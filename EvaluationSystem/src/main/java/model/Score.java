package model;

public class Score {
    private Submission submission;
    private float score;

    public Score(Submission submission){
        this.submission = submission;
        float score = submission.getScore();
        this.score = score > 20 ? 20:score;
    }

    public Score(){
        this.submission = null;
        this.score = 0;
    }

    public Score(float score){
        this.submission = null;
        this.score = score;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
