package wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import model.Score;
import model.Submission;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScoreWrapper {

    private Integer submissionID;
    private float score;

    public ScoreWrapper(){}

    public ScoreWrapper(Score score){
        Submission submission = score.getSubmission();
        if(submission != null) {
            this.score = submission.getScore();
            this.submissionID = submission.getID();
        }
        else{
            this.score = 0.0f;
            this.submissionID = null;
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
}
