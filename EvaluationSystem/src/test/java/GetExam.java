import dao.ExamDAO;
import dao.UserDAO;
import model.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import serializer.ExamSerializationMode;
import serializer.ExamSerializer;
import service.UserService;

@SpringBootApplication
@ComponentScan(basePackages = {"controller","service","dao","serializer"})
public class Test implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(Test.class,args);
    }

    @Autowired
    UserDAO userDAO;
    @Autowired
    UserService userService;
    @Autowired
    ExamDAO examDAO;
    @Autowired
    ExamSerializer examSerializer;

    @Override
    public void run(String... strings) throws Exception {
//        Teacher[] teachers = userService.getTeachers();
//        Teacher teacher0 = teachers[0];
//
//        Class cl = teacher0._classes.toArray()[0];
//
//        Group group = new Group();
//        group.setName("2016/2017");
//
//        cl._groups.add(group);
//
//        model.Exam exam = new model.Exam();
//        exam.setBeginDate(new Date(System.currentTimeMillis()));
//
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.HOUR_OF_DAY,2);
//        cal.set(Calendar.MINUTE,30);
//        cal.set(Calendar.SECOND,0);
//
//        exam.setDuration(cal.getTime());
//
//        group._exams.add(exam);
//
//        userDAO.save(teacher0);

        Exam exam = examDAO.getExamByORMID(1);
        String examSrl = examSerializer.serialize(exam, ExamSerializationMode.CLASS);
        System.out.println(examSrl);
    }
}
