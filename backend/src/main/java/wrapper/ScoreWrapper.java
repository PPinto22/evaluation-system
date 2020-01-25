package wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import model.Score;
import model.Submission;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScoreWrapper {

    private Integer submissionID;
    private float score;
    private Integer total;
    private Integer correct;

    public ScoreWrapper(){}

    public ScoreWrapper(Score score){
        Submission submission = score.getSubmission();
        this.score = score.getScore();
        this.total = score.getTotal();
        this.correct = score.getCorrect();

        if (score.getSubmission()!=null)
            this.submissionID = submission.getID();

    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Integer getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(Integer submissionID) {
        this.submissionID = submissionID;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }
}
