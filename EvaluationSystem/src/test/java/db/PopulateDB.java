package db;

import exception.ExistentEntityException;
import exception.MissingInformationException;
import model.*;
import model.Class;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import service.*;

import java.util.HashMap;
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

    public static int userCount = 0;
    public static int classCount = 0;
    public static int groupCount = 0;

    private static final int N_TEACHERS = 10;
    private static final int N_STUDENTS = 10;
    public static final int N_TEACHER_CLASSES = 2;
    public static final int N_CLASS_GROUPS = 2;
    public static final int N_GROUPS_STUDENTS = 5;

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
    }

    private void addStudentsToGroups() {
        // TODO
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
        groupCount++;
        Group group = new Group();
        group.setName("Name"+groupCount);
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
        classCount++;
        Class cl = new Class();
        cl.setAbbreviation("Abbreviation"+classCount);
        cl.setName("Name"+classCount);
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
        userCount++;
        User signup = new User();
        signup.setEmail("email"+userCount);
        signup.setPassword("password");
        signup.setFirstName("firstName"+userCount);
        signup.setLastName("lastName"+userCount);

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