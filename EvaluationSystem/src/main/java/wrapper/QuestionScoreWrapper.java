package wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import model.persistent.QuestionScore;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionScoreWrapper extends QuestionWrapper implements Comparable<QuestionScoreWrapper>{

    private float score;
    private Integer order;

    public QuestionScoreWrapper(){}

    public QuestionScoreWrapper(QuestionScore questionScore, boolean hideCorrectAnswers){
        super(questionScore.get_question(),hideCorrectAnswers);
        this.score = questionScore.getScore();
        this.order = questionScore.getOrder();
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public int compareTo(QuestionScoreWrapper o) {
        if (this.getOrder() > o.getOrder())
            return 1;
        else if(this.getOrder() < o.getOrder())
            return -1;
        else
            return 0;
    }
}
