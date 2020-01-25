package model;

public class Score {
    private Submission submission;
    private float score;
    private Integer total;
    private Integer correct;

    public Score(Submission submission){
        this.submission = submission;
        float score = submission.getScore();
        this.score = score > 20 ? 20:score;
        this.total = submission.get_exam()._questions.size();
        this.correct = 0;
        for(QuestionSubmission qSub: submission._questionSubmissions.toArray()){
            if(qSub.isCorrect())
                this.correct++;
        }
    }

    public Score(){
        this.submission = null;
        this.score = 0;
        this.total = null;
        this.correct = null;
    }

    public Score(float score){
        this.submission = null;
        this.score = score;
        this.total = null;
        this.correct = null;
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
