package db;

import dao.ClassesPersistentManager;
import exception.*;
import model.*;
import model.Class;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import service.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "dao", "service", "security"})
public class PopulateDB implements CommandLineRunner {

    private static PersistentSession session = null;

    public static final int N_TEACHERS = 20;
    public static final int N_STUDENTS = 1000;
    public static final int N_CLASSES_PER_TEACHER = 2;
    public static final int N_GROUPS_PER_CLASS = 5;
    public static final int N_STUDENTS_PER_GROUP = 20;
    public static final int N_QUESTIONS_PER_CLASS = 50;
    public static final int N_CATEGORIES_PER_CLASS = 5;
    public static final int N_EXAMS_PER_GROUP = 2;
    public static final int N_QUESTIONS_PER_EXAM = 20;

    private Map<Integer, Student> students = new HashMap<>();
    private Map<Integer, Teacher> teachers = new HashMap<>();
    private Map<Integer, Class> classes = new HashMap<>();
    private Map<Integer, Group> groups = new HashMap<>();
    private Map<Integer, Exam> exams = new HashMap<>();


    public static void main(String[] args) throws Exception {
        SpringApplication.run(PopulateDB.class, args);
        System.exit(0);
    }

    private PersistentSession getSession() throws PersistentException {
        if(session == null || !session.isOpen())
            session = ClassesPersistentManager.instance().getSession();
        return session;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Creating students...");
        addStudents();
        System.out.println("Creating teachers...");
        addTeachers();
        System.out.println("Adding classes to teachers...");
        addClasses();
        System.out.println("Adding groups to classes...");
        addGroups();
        System.out.println("Adding students to groups...");
        addStudentsToGroups();
        System.out.println("Accepting group invitations...");
        acceptGroupInvitations();
        System.out.println("Adding questions to classes...");
        addQuestionsToClasses();
        System.out.println("Creating exams and submissions...");
        addExamsAndSubmissions();
    }

    private void addExamsAndSubmissions() throws Exception {
        for(Group group: this.groups.values()){
            List<Question> classQuestions = Arrays.asList(group.get_class()._question.toArray());
            for(int i=0; i<N_EXAMS_PER_GROUP; i++){
                int exam_i = this.exams.size()+1;
                String name = "Exam"+exam_i;
                long date = System.currentTimeMillis();
                int duration = 10;
                List<Integer> questionIDs = new ArrayList<>();
                for(int qi = 0; qi<N_QUESTIONS_PER_EXAM; qi++){
                    Question question = classQuestions.get(qi*i+qi);
                    questionIDs.add(question.getID());
                }
                Exam exam = examService.createExam(session, name, duration, date, questionIDs, group);
                examService.addExamToGroup(session,group,exam);
                this.exams.put(exam.getID(), exam);
                addSubmissionsToExam(exam);
            }
        }
    }

    private void addSubmissionsToExam(Exam exam) throws Exception{
        List<Student> groupStudents = groupSrv.getAcceptedStudents(exam.get_group());
        for(Student student: groupStudents){
            addSubmissionToExam(student, exam);
        }
    }

    private void addSubmissionToExam(Student student, Exam exam) throws Exception {
        Map<Question, Answer> answerMap = new HashMap<>();
        for(QuestionScore questionScore: exam._questions.toArray()){
            Question question = questionScore.get_question();
            List<Answer> questionAnswers = question.getAnswers();
            int randomAnswer_i = ThreadLocalRandom.current().nextInt(0, questionAnswers.size());
            answerMap.put(question, questionAnswers.get(randomAnswer_i));
        }
        submissionService.submit(session, student, exam, answerMap);
    }


    private void addQuestionsToClasses() {
        for(Class cl: this.classes.values()){
            for(Question question: this.getQuestions())
                try{
                    classSrv.addQuestionToClass(getSession(), cl, question);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
        }
    }

    private List<Question> getQuestions(){
        List<Question> questions = new ArrayList<>();

        for(int i = 0; i<N_QUESTIONS_PER_CLASS; i++) {
            int category_i = i%N_CATEGORIES_PER_CLASS + 1;
            int difficulty = i%3 + 1;
            Question question = new Question();
            question.setCategory("Category"+category_i);
            question.setDifficulty(difficulty);
            String text = String.format("Solve for x: %d + x = %d", i, i+4);
            question.setText(text);
            for(int j = 1; j<=4; j++){
                Answer answer = new Answer();
                answer.setText(""+(i+j));
                if(j == 4)
                    answer.setCorrect(true);
                else
                    answer.setCorrect(false);
                answer.setOrder(j-1);
                question._answers.add(answer);
            }
            questions.add(question);
        }

        return questions;
    }

    private void acceptGroupInvitations() {
        for(Student student: this.students.values()){
            for(Notification notification: student._notifications.toArray()){
                switch (notification.getClass().getSimpleName()){
                    case "GroupInvitation":
                        GroupInvitation invitation = (GroupInvitation)notification;
                        try{
                            notifSrv.acceptInvitation(getSession(), invitation);
                        } catch (UnconfirmedRegistrationException e) {
                            // Utilizador nao registado; nao e possivel aceitar.
                        } catch (PersistentException e) {
                            e.printStackTrace();
                        }
                    default:
                        break;
                }
            }
        }
    }

    private void addStudentsToGroups() {
        int group_i = 0;
        for(Group group: this.groups.values()){
            for(int i = 0; i<N_STUDENTS_PER_GROUP - 1; i++){
                int j = (group_i*(N_STUDENTS_PER_GROUP-1) + i) % (N_STUDENTS-1) + 1;
                try {
                    Student student = this.students.get(j);
                    GroupStudent groupStudent = groupSrv.addStudentToGroupByEmail(getSession(),
                            group,
                            student.getEmail());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try{
                GroupStudent groupStudent = groupSrv.addStudentToGroupByEmail(getSession(),
                        group,
                        "email"+(this.students.size()+this.teachers.size()+1)+"@gmail.com");
                Student student = groupStudent.get_student();
                this.students.put(student.getID(),student);
            } catch (Exception e){ e.printStackTrace(); }
            group_i++;
        }
    }

    private void addGroups() {
        for(Class cl: this.classes.values()){
            for(int i = 0; i<N_GROUPS_PER_CLASS; i++){
                try {
                    this.addGroupToClass(cl);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void addGroupToClass(Class cl) throws Exception {
        int i = this.groups.size()+1;
        Group group = new Group();
        group.setName("Name"+i);
        group = this.classSrv.addGroupToClass(getSession(), cl, group);
        this.groups.put(group.getID(), group);
    }

    private void addClasses() {
        for(Teacher teacher: this.teachers.values()){
            for(int i = 0; i<N_CLASSES_PER_TEACHER; i++)
                try {
                    this.addClassToTeacher(teacher);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
        }
    }

    private void addClassToTeacher(Teacher teacher) throws MissingInformationException, PersistentException, ExistentEntityException {
        int i = this.classes.size()+1;
        Class cl = new Class();
        cl.setAbbreviation("Abbreviation"+i);
        cl.setName("Name"+i);
        cl = teacherSrv.addClassToTeacher(getSession(), teacher, cl);
        this.classes.put(cl.getID(), cl);
    }

    private void addTeachers() {
        for(int i = 0; i<N_TEACHERS; i++)
            try {
                this.addUser("teacher");
            }
            catch (Exception e){
                e.printStackTrace();
            }
    }

    private void addStudents() {
        for(int i = 0; i<N_STUDENTS; i++) {
            try {
                this.addUser("student");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void addUser(String type) throws Exception {
        int i = this.students.size()+this.teachers.size()+1;
        User signup = new User();
        signup.setEmail("email"+i+"@gmail.com");
        signup.setPassword("password");
        signup.setFirstName("firstName"+i);
        signup.setLastName("lastName"+i);

        User user = userSrv.signup(getSession(), signup, type, true);

        switch (type){
            case "student":
                Student student = (Student) user;
                this.students.put(user.getID(), student);
                break;
            case "teacher":
                Teacher teacher = (Teacher) user;
                this.teachers.put(user.getID(), teacher);
                break;
        }
    }


    @Autowired
    UserService userSrv;
    @Autowired
    StudentService studentSrv;
    @Autowired
    TeacherService teacherSrv;
    @Autowired
    ClassService classSrv;
    @Autowired
    GroupService groupSrv;
    @Autowired
    NotificationService notifSrv;
    @Autowired
    ExamService examService;
    @Autowired
    SubmissionService submissionService;
}