package db;

import exception.ExistentEntityException;
import exception.MissingInformationException;
import exception.UnconfirmedRegistrationException;
import model.persistent.*;
import model.persistent.Class;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import service.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "dao", "service", "security"})
public class PopulateDB implements CommandLineRunner {

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

    public static final int N_TEACHERS = 10;
    public static final int N_STUDENTS = 10;
    public static final int N_TEACHER_CLASSES = 2;
    public static final int N_CLASS_GROUPS = 2;
    public static final int N_GROUPS_STUDENTS = 5;
    public static final int N_QUESTIONS_CLASS = 45;
    public static final int N_CATEGORIES = 5;

    private Map<Integer, Student> students = new HashMap<>();
    private Map<Integer, Teacher> teachers = new HashMap<>();
    private Map<Integer, Class> classes = new HashMap<>();
    private Map<Integer, Group> groups = new HashMap<>();


    public static void main(String[] args) throws Exception {
        SpringApplication.run(PopulateDB.class, args);
        System.exit(0);
    }

    @Override
    public void run(String... args) {
        addStudents();
        addTeachers();
        addClasses();
        addGroups();
        addStudentsToGroups();
        acceptGroupInvitations();
        addQuestionsToClasses();
    }

    private void addQuestionsToClasses() {
        for(Class cl: this.classes.values()){
            for(Question question: this.getQuestions())
                try{
                    classSrv.addQuestionToClass(cl, question);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
        }
    }

    private List<Question> getQuestions(){
        List<Question> questions = new ArrayList<>();

        for(int i = 0; i<N_QUESTIONS_CLASS; i++) {
            int category_i = i%N_CATEGORIES + 1;
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
                            notifSrv.acceptInvitation(invitation);
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
            for(int i = 0; i<N_GROUPS_STUDENTS - 1; i++){
                int j = (group_i*(N_GROUPS_STUDENTS-1) + i) % (N_STUDENTS-1) + 1;
                try {
                    Student student = this.students.get(j);
                    GroupStudent groupStudent = groupSrv.addStudentToGroupByEmail(
                            group, student.getEmail()
                            );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try{
                GroupStudent groupStudent = groupSrv.addStudentToGroupByEmail(
                        group,
                        "email"+this.students.size()+this.teachers.size()+1
                );
                Student student = groupStudent.get_student();
                this.students.put(student.getID(),student);
            } catch (Exception e){ e.printStackTrace(); }
            group_i++;
        }
    }

    private void addGroups() {
        for(Class cl: this.classes.values()){
            for(int i = 0; i<N_CLASS_GROUPS; i++){
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
        group = this.classSrv.addGroupToClass(cl, group);
        this.groups.put(group.getID(), group);
    }

    private void addClasses() {
        for(Teacher teacher: this.teachers.values()){
            for(int i = 0; i<N_TEACHER_CLASSES; i++)
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
        cl = teacherSrv.addClassToTeacher(teacher, cl);
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
        signup.setEmail("email"+i);
        signup.setPassword("password");
        signup.setFirstName("firstName"+i);
        signup.setLastName("lastName"+i);

        User user = userSrv.signup(signup, type, true);

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

}