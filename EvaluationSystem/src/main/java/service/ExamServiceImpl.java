package service;

import dao.ExamDAO;
import dao.QuestionDAO;
import dao.QuestionScoreDAO;
import exception.*;
import model.*;
import model.Class;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ExamServiceImpl implements ExamService{

    QuestionScoreDAO questionScoreDAO;
    ExamDAO examDAO;
    QuestionDAO questionDAO;
    StudentService studentService;
    TeacherService teacherService;
    SubmissionService submissionService;
    GroupService groupService;

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }
    @Autowired
    public void setSubmissionService(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }
    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public ExamServiceImpl(QuestionScoreDAO questionScoreDAO, ExamDAO examDAO, QuestionDAO questionDAO) {
        this.questionScoreDAO = questionScoreDAO;
        this.examDAO = examDAO;
        this.questionDAO = questionDAO;
    }

    @Override
    public Map<Student, Score> getExamScores(Exam exam) throws PersistentException, InvalidExamException {
        if(!examHasFinished(exam))
            throw new InvalidExamException();

        Map<Student, Score> studentMap = new TreeMap<>();
        for(GroupStudent groupStudent: exam.get_group()._students.toArray()){
            Student student = groupStudent.get_student();
            try {
                Submission submission = submissionService.getSubmissionByStudentAndExam(student, exam);
                studentMap.put(student, new Score(submission));
            }catch (NonExistentEntityException e) {
                studentMap.put(student, new Score());
            }
        }
        return studentMap;
    }

    @Override
    public QuestionScore getQuestionScore(Exam exam, Question question) throws NonExistentEntityException {
        for(QuestionScore qScore: exam._questions.toArray()){
            if(qScore.get_question().getID() == question.getID())
                return qScore;
        }
        throw new NonExistentEntityException();
    }

    @Override
    public Map<String, Set<Exam>> getExamsByUser(User user) {
        Map<String, Set<Exam>> examMap = new TreeMap<>();
        List<Group> groups = null;
        switch (user.getClass().getSimpleName()){
            case "Student":
                groups = studentService.getStudentGroups((Student)user);
                break;
            case "Teacher":
                groups = teacherService.getGroups((Teacher)user);
                break;
        }
        return getGroupsExams(groups);
    }

    private Map<String, Set<Exam>> getGroupsExams(List<Group> groups){
        Map<String, Set<Exam>> examMap = new TreeMap<>();
        for(Group group: groups){
            for(Exam exam: group.getExams()){
                String key = examHasStarted(exam) ? examHasFinished(exam) ? "History" : "Ongoing" : "Upcoming";
                if(!examMap.containsKey(key))
                    examMap.put(key, new TreeSet<>());
                examMap.get(key).add(exam);
            }
        }
        return examMap;
    }

    @Override
    public Map<String, Set<Exam>> getExamsByClass(Class cl) {
        Map<String, Set<Exam>> examMap = new TreeMap<>();
        List<Group> groups = Arrays.asList(cl._groups.toArray());
        return getGroupsExams(groups);
    }

    @Override
    public Map<String, Set<Exam>> getExamsByGroup(Group group) {
        Map<String, Set<Exam>> examMap = new TreeMap<>();
        for(Exam exam: group.getExams()){
            String key = examHasStarted(exam) ? examHasFinished(exam) ? "History" : "Ongoing" : "Upcoming";
            if(!examMap.containsKey(key))
                examMap.put(key, new TreeSet<>());
            examMap.get(key).add(exam);
        }
        return examMap;
    }

    @Override
    public Exam getExamByID(int examID) throws PersistentException, NonExistentEntityException {
        if(!exists(examID))
            throw new NonExistentEntityException();
        return examDAO.loadExamByORMID(examID);
    }

    @Override
    public boolean examHasStarted(Exam exam) {
        long ms = System.currentTimeMillis();
        return ms > exam.getBeginDate();
    }

    @Override
    public boolean examHasFinished(Exam exam) {
        long ms = System.currentTimeMillis();
        return ms > (exam.getBeginDate() + exam.getDuration()*60*1000 + 5*60*1000); // 5 minutos de tolerancia apos fim
    }

    @Override
    public Exam createExam(String name, int minutes, long beginDate, List<Integer> questionIDs, Group group)
            throws InvalidExamException, PersistentException, InvalidQuestionException, ExistentEntityException {
        Exam exam = new Exam();
        if(name == null || name.equals(""))
            throw new InvalidExamException("name");
        if(minutes < 10 || minutes > 300)
            throw new InvalidExamException("duration");
        //if(beginDate < (System.currentTimeMillis()+ (1000 * 60 * 60 * 24)))
        //    throw new InvalidExamException("date");
        if(exists(group, name))
            throw new ExistentEntityException();
        if(hasDuplicates(questionIDs))
            throw new InvalidExamException("duplicates");

        exam.setName(name);
        exam.setDuration(minutes);
        exam.setBeginDate(beginDate);
        exam.set_group(group);
        Class cl = group.get_class();
        for (int i = 0; i<questionIDs.size(); i++) {
            int qid = questionIDs.get(i);
            Question question = questionDAO.getQuestionByORMID(qid);
            if(question == null || question.get_class().getID() != cl.getID())
                throw new InvalidQuestionException(""+qid);
            QuestionScore questionScore = new QuestionScore();
            questionScore.set_question(question);
            questionScore.setScore(20.0f / questionIDs.size()); //FIXME, DEFINIR COTACOES
            questionScore.set_exam(exam);
            questionScore.setOrder(i);
            exam._questions.add(questionScore);
        }
        return exam;
    }

    private boolean hasDuplicates(List<?> list){
        for(int i = 0; i<list.size(); i++){
            for(int j = i+1; j<list.size(); j++){
                if(list.get(i).equals(list.get(j)))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean exists(int examID) throws PersistentException {
        return examDAO.exists(examID);
    }

    @Override
    public boolean exists(Group group, String examName) throws PersistentException {
        return examDAO.exists(group.getID(),examName);
    }

    @Override
    public boolean examContainsQuestion(Exam exam, Question question) throws PersistentException {
        return questionScoreDAO.exists(question.getID(),exam.getID());
    }

    @Override
    public Exam addExamToGroup(Group group, Exam exam) throws PersistentException, ExistentEntityException {
        if(exists(group, exam.getName()))
            throw new ExistentEntityException();
        exam.set_group(group);
        group._exams.add(exam);
        examDAO.save(exam);
        return exam;
    }

    @Override
    public Question generateExamQuestion(Group group, String category, int difficulty, List<Question> excludedQuestions) throws PersistentException, InsufficientQuestionsException {
        List<Question> availableQuestions = groupService.listAvailableQuestionByCategoryAndDifficulty(group,category,difficulty);
        for(Question question: excludedQuestions){
            if(availableQuestions.contains(question))
                availableQuestions.remove(question);
        }

        if(availableQuestions.isEmpty())
            throw new InsufficientQuestionsException();

        int randomQuestionIndex = ThreadLocalRandom.current().nextInt(0, availableQuestions.size());
        return availableQuestions.get(randomQuestionIndex);
    }

    @Override
    public List<Question> generateExamQuestions(Group group, List<String> categories, List<Integer> difficulties)
            throws PersistentException, InvalidInputException, InsufficientQuestionsException {
        if(categories.size() != difficulties.size())
            throw new InvalidInputException("Categories and dificulties lists must be the same size");

        Map<String, Map<Integer, List<Question>>> categoriesMap = groupService.getAvailableQuestions(group);
        List<Question> res = new ArrayList<>();
        for(int i = 0; i<categories.size(); i++){
            String category = categories.get(i);
            int difficulty = difficulties.get(i);
            if(!categoriesMap.containsKey(category))
                throw new InsufficientQuestionsException();
            Map<Integer, List<Question>> difficultiesMap = categoriesMap.get(category);
            if(!difficultiesMap.containsKey(difficulty))
                throw new InsufficientQuestionsException();
            List<Question> questions = difficultiesMap.get(difficulty);
            if(questions.isEmpty())
                throw new InsufficientQuestionsException();

            int randomQuestionIndex =  ThreadLocalRandom.current().nextInt(0, questions.size());
            Question selectedQuestion = questions.get(randomQuestionIndex);
            questions.remove(randomQuestionIndex);

            res.add(selectedQuestion);
        }

        return res;
    }
}
