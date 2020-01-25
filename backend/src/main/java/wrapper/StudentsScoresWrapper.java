package wrapper;


import model.Score;
import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentsScoresWrapper{
    private List<StudentScoreWrapper> students;

    public StudentsScoresWrapper(){}

    public StudentsScoresWrapper(Map<Student, Score> scores){
        this.students = new ArrayList<>();
        for(Student student: scores.keySet()){
            Score score = scores.get(student);
            this.students.add(new StudentScoreWrapper(student, score));
        }
    }

    public List<StudentScoreWrapper> getStudents() {
        return students;
    }

    public void setStudents(List<StudentScoreWrapper> students) {
        this.students = students;
    }
}
