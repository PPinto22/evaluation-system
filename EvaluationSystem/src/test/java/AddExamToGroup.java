import dao.ExamDAO;
import dao.ExamDAOImpl;
import dao.GroupDAO;
import dao.GroupDAOImpl;
import model.persistent.Exam;
import model.persistent.Group;
import org.orm.PersistentException;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddExamToGroup {
    public static void main(String[] args) {
        Exam exam = new Exam();
        exam.setBeginDate(new Timestamp(System.currentTimeMillis()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse("02:30");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Time time = new Time(parsedDate.getTime());
        exam.setDuration(time);

        GroupDAO groupDAO = new GroupDAOImpl();
        Group group = null;
        try {
            group = groupDAO.getGroupByORMID(1);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        exam.set_group(group);

        ExamDAO examDAO = new ExamDAOImpl();
        try {
            examDAO.save(exam);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }
}
