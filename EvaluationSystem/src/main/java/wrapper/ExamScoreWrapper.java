package wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import model.Exam;
import model.Score;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamScoreWrapper{

    private ExamWrapper exam;
    private ScoreWrapper score;

    public ExamScoreWrapper(){}

    public ExamScoreWrapper(Exam exam, Score score, boolean hideExamClass){
        if(hideExamClass)
            this.exam = new ExamWrapper(exam,true,true);
        else
            this.exam = new ExamClassWrapper(exam,true,true);
        this.score = new ScoreWrapper(score);
    }

    public ExamWrapper getExam() {
        return exam;
    }

    public void setExam(ExamWrapper exam) {
        this.exam = exam;
    }

    public ScoreWrapper getScore() {
        return score;
    }

    public void setScore(ScoreWrapper score) {
        this.score = score;
    }
}
