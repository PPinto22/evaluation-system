package wrapper;

import model.persistent.QuestionScore;

public class QuestionScoreWrapper extends QuestionWrapper implements Comparable<QuestionScoreWrapper>{

    private float score;

    public QuestionScoreWrapper(){}

    public QuestionScoreWrapper(QuestionScore questionScore, boolean hideCorrectAnswers){
        super(questionScore.get_question(),hideCorrectAnswers);
        this.score = questionScore.getScore();
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public int compareTo(QuestionScoreWrapper o) {
        if (this.getId() > o.getId())
            return 1;
        else if(this.getId() < o.getId())
            return -1;
        else
            return 0;
    }
}
