import dao.ExamDAO;
import model.Exam;
import model.User;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import serializer.*;
import util.Colors;

@SpringBootApplication
@ComponentScan(basePackages = {"controller","service","dao","serializer"})
public class GetExam implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(GetExam.class,args);
    }

    @Autowired
    ExamDAO examDAO;
    @Autowired
    ExamSerializer examSerializer;

    @Override
    public void run(String... strings) throws Exception {
        try {
            Exam exam = examDAO.getExamByORMID(1);

            System.out.println(Colors.BLUE);

            System.out.println(exam.getBeginDateAsString());
            System.out.println(exam.getDurationAsString());

            System.out.println(Colors.RESET);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    class Asdasd{
        public String nome = "ola";
    }
}
