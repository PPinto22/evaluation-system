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
        if(submission != null) {
            this.score = submission.getScore();
            this.submissionID = submission.getID();
            this.total = score.getTotal();
            this.correct = score.getCorrect();
        }
        else{
            this.score = 0.0f;
            this.submissionID = null;
            this.total = null;
            this.correct = null;
        }
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
