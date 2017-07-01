package service;

import dao.GroupDAO;
import dao.GroupStudentDAO;
import exception.*;
import model.*;
import model.Class;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupServiceImpl implements GroupService{

    GroupDAO groupDAO;
    GroupStudentDAO groupStudentDAO;
    UserService userService;
    StudentService studentService;
    NotificationService notificationService;
    ClassService classService;
    ExamService examService;
    SubmissionService submissionService;

    @Autowired
    public void setSubmissionService(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }
    @Autowired
    public void setExamService(ExamService examService) {
        this.examService = examService;
    }
    @Autowired
    public void setClassService(ClassService classService) {
        this.classService = classService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public GroupServiceImpl(GroupDAO groupDAO, GroupStudentDAO groupStudentDAO) {
        this.groupDAO = groupDAO;
        this.groupStudentDAO = groupStudentDAO;
    }

    @Override
    public Group updateGroup(PersistentSession session, Group group, String name) throws PersistentException, ExistentEntityException {
        if(name != null && !name.equals(group.getName()) && exists(session, group.get_class(), name))
            throw new ExistentEntityException();

        if(name != null && !name.equals(""))
            group.setName(name);
        groupDAO.save(group);
        return group;
    }

    @Override
    public Map<Student, Map<Exam, Score>> getGroupScores(PersistentSession session, Group group) throws PersistentException {
        Map<Student, Map<Exam, Score>> studentMap = new TreeMap<>();
        for(GroupStudent groupStudent: group._students.toArray()){
            Student student = groupStudent.get_student();
            Map<Exam, Score> examMap = new TreeMap<>();
            for(Exam exam: group.getExams()){
                if(examService.examHasFinished(exam)) {
                    try {
                        Submission submission = submissionService.getSubmissionByStudentAndExam(session, student, exam);
                        examMap.put(exam, new Score(submission));
                    } catch (NonExistentEntityException e) {
                        examMap.put(exam, new Score());
                    }
                }
            }
            studentMap.put(student, examMap);
        }
        return studentMap;
    }

    @Override
    public boolean userHasAccess(Group group, User user) {
        if(user instanceof Teacher)
            return user.getID() == group.get_class().get_teacher().getID();
        Student student = (Student) user;
        return studentInGroup(student,group);
    }

    @Override
    public boolean exists(PersistentSession session, int ID) throws PersistentException {
        return this.groupDAO.exists(session, ID);
    }

    @Override
    public boolean exists(PersistentSession session, Class cl, String name) throws PersistentException {
        return this.groupDAO.exists(session, cl, name);
    }

    @Override
    public Group getGroupByID(PersistentSession session, int ID) throws PersistentException, NonExistentEntityException {
        if(!this.exists(session, ID))
            throw new NonExistentEntityException();

        else return groupDAO.loadGroupByORMID(ID);
    }

    @Override
    public Group getGroupByName(PersistentSession session, Class cl, String name) throws PersistentException, NonExistentEntityException {
        if(!this.exists(session, cl, name))
            throw new NonExistentEntityException();

        else return groupDAO.loadGroupByName(session, cl, name);
    }

    @Override
    public Group addGroup(Group group) throws PersistentException {
        this.groupDAO.save(group);
        return group;
    }

    @Override
    public void delete(PersistentSession session, Group group) throws PersistentException, EntityNotRemovableException {
        if(groupHasSubmissions(session, group))
            throw new EntityNotRemovableException();

        for(Exam exam: group._exams.toArray()){
            examService.deleteExam(session, exam);
        }

        for(GroupStudent groupStudent: group._students.toArray()){
            try {
                GroupInvitation groupInvitation = notificationService.getGroupInvitation(session, group, groupStudent.get_student());
                notificationService.removeGroupInvitation(groupInvitation);
            } catch(NonExistentEntityException e){}
            groupStudentDAO.deleteAndDissociate(groupStudent);
        }

        group.get_class()._groups.remove(group);
        this.groupDAO.delete(group);
    }

    @Override
    public boolean groupHasSubmissions(PersistentSession session, Group group) throws PersistentException {
        for(Exam exam: group._exams.toArray()){
            if(examService.examHasSubmissions(session, exam))
                return true;
        }
        return false;
    }

    @Override
    public List<GroupStudent> getGroupStudents(Group group) {
        return Arrays.asList(group._students.toArray());
    }

    @Override
    public GroupStudent addStudentToGroupByEmail(PersistentSession session, Group group, String email)
            throws PersistentException, InvalidUserTypeException, ExistentEntityException {
        Student student = null;
        if(!userService.existsActive(session, email)){
            if(!userService.exists(session, email)) {
                try {
                    student = studentService.createStudent();
                    student.setEmail(email);
                    student = studentService.addStudent(session, student, false);
                } catch (MissingInformationException e) {
                    e.printStackTrace(); // Nunca acontece
                } catch (ExistentEntityException e) {
                    e.printStackTrace();
                    throw new PersistentException(); // Nunca deve acontecer
                }
            }
            else{
                try {
                    student = studentService.getStudentByEmail(session, email);
                } catch (NonExistentEntityException e) {
                    e.printStackTrace(); //Nunca deve acontecer
                }
            }
        }
        else{
            if(studentService.existsActive(session, email)) {
                try {
                    student = studentService.getStudentByEmail(session, email);
                } catch (NonExistentEntityException e) {
                    e.printStackTrace(); // Nunca deve acontecer
                }
            }
            else
                throw new InvalidUserTypeException();
        }

        if(groupStudentDAO.exists(session, group.getID(), student.getID()))
            throw new ExistentEntityException();
        return this.addStudentToGroup(group,student);
    }

    private GroupStudent addStudentToGroup(Group group, Student student) throws PersistentException {
        GroupStudent groupStudent = this.createGroupStudent(group,student);
        this.groupStudentDAO.save(groupStudent);
        this.notificationService.addGroupInvitation(group, student);
        return groupStudent;
    }

    @Override
    public void removeStudentFromGroup(PersistentSession session, Group group, Student student)
            throws PersistentException, NonExistentEntityException, EntityNotRemovableException {
        GroupStudent groupStudent = this.groupStudentDAO.loadGroupStudentByGroupAndStudent(session,
                group.getID(), student.getID());
        if(groupStudent == null)
            throw new NonExistentEntityException();

        if(groupStudent.isAccepted()){
            for(Exam exam: group._exams.toArray()){
                if(submissionService.exists(session, student, exam))
                    throw new EntityNotRemovableException();
            }
        }

        student._groups.remove(groupStudent);
        group._students.remove(groupStudent);
        this.groupStudentDAO.delete(groupStudent);
        try {
            GroupInvitation groupInvitation = notificationService.getGroupInvitation(session, group, student);
            notificationService.removeGroupInvitation(groupInvitation);
        } catch(NonExistentEntityException e){}
    }

    private GroupStudent createGroupStudent(Group group, Student student){
        GroupStudent groupStudent = new GroupStudent();
        groupStudent.set_group(group);
        groupStudent.set_student(student);
        groupStudent.setAccepted(false);
        return groupStudent;
    }

    @Override
    public boolean studentInGroup(Student student, Group group) {
        for(GroupStudent groupStudent: group._students.toArray()) {
            if (groupStudent.get_student().getID() == student.getID())
                return true;
        }
        return false;
    }

    @Override
    public boolean questionInExams(PersistentSession session, Group group, Question question) throws PersistentException {
        for(Exam exam: group._exams.toArray()){
            if(examService.examContainsQuestion(session, exam, question))
                return true;
        }
        return false;
    }

    @Override
    public List<Question> listAvailableQuestions(PersistentSession session, Group group) throws PersistentException {
        List<Question> classQuestions = classService.listClassQuestions(session, group.get_class());
        List<Question> availableQuestions = new ArrayList<>();
        for(Question q: classQuestions){
            if(!this.questionInExams(session, group, q))
                availableQuestions.add(q);
        }
        return availableQuestions;
    }

    @Override
    public List<Question> listAvailableQuestionByCategoryAndDifficulty(PersistentSession session, Group group, String category, int difficulty) throws PersistentException {
        List<Question> classQuestions = classService.listClassQuestionsByCategoryAndDifficulty(session, group.get_class(), category, difficulty);
        List<Question> availableQuestions = new ArrayList<>();
        for(Question q: classQuestions){
            if(!this.questionInExams(session, group, q))
                availableQuestions.add(q);
        }
        return availableQuestions;
    }

    @Override
    public Map<String, Map<Integer, List<Question>>> getAvailableQuestions(PersistentSession session, Group group)
            throws PersistentException {
        List<Question> questions = this.listAvailableQuestions(session, group);
        Map<String, Map<Integer, List<Question>>> categoriesMap = new TreeMap<>();
        for(Question question: questions){
            String category = question.getCategory();
            int difficulty = question.getDifficulty();
            if(!categoriesMap.containsKey(category))
                categoriesMap.put(category, new TreeMap<Integer, List<Question>>());
            Map<Integer, List<Question>> difficultiesMap = categoriesMap.get(category);
            if(!difficultiesMap.containsKey(difficulty))
                difficultiesMap.put(difficulty, new ArrayList<Question>());

            categoriesMap.get(category).get(difficulty).add(question);
        }
        return categoriesMap;
    }
}
