package wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import model.Exam;
import model.Score;
import model.Student;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentExamsScoresWrapper extends ExamsScoresWrapper {

    private UserWrapper student;

    public StudentExamsScoresWrapper() {
        super();
    }

    public StudentExamsScoresWrapper(Student student, Map<Exam, Score> scoreMap) {
        super(scoreMap);
        this.student = new UserWrapper(student);
    }

    public UserWrapper getStudent() {
        return student;
    }

    public void setStudent(UserWrapper student) {
        this.student = student;
    }
}

