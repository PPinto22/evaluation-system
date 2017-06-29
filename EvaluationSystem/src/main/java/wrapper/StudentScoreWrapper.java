package wrapper;

import model.Score;
import model.Student;

public class StudentScoreWrapper {

    private UserWrapper student;
    private ScoreWrapper score;

    public StudentScoreWrapper(){}

    public StudentScoreWrapper(Student student, Score score){
        this.student = new UserWrapper(student);
        this.score = new ScoreWrapper(score);
    }

    public UserWrapper getStudent() {
        return student;
    }

    public void setStudent(UserWrapper student) {
        this.student = student;
    }

    public ScoreWrapper getScore() {
        return score;
    }

    public void setScore(ScoreWrapper score) {
        this.score = score;
    }
}
