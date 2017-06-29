package wrapper;

import model.Exam;
import model.Score;
import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentsExamsScoresWrapper {

    private List<StudentExamsScoresWrapper> students;

    public StudentsExamsScoresWrapper(){}

    public StudentsExamsScoresWrapper(Map<Student, Map<Exam, Score>> scores){
        this.students = new ArrayList<>();
        for(Student student: scores.keySet()){
            Map<Exam, Score> examsScores = scores.get(student);
            this.students.add(new StudentExamsScoresWrapper(student, examsScores));
        }
    }

    public List<StudentExamsScoresWrapper> getStudents() {
        return students;
    }

    public void setStudents(List<StudentExamsScoresWrapper> students) {
        this.students = students;
    }
}
