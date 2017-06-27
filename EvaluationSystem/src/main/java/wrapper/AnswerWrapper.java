package wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import model.persistent.Answer;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerWrapper implements Comparable<AnswerWrapper>{

    private int id;
    private String text;
    private Boolean correct;
    private Integer order;

    public AnswerWrapper(){}

    public AnswerWrapper(Answer answer, boolean hideCorrect){
        this.id = answer.getID();
        this.text = answer.getText();
        if(hideCorrect)
            this.correct = null;
        else
            this.correct = answer.isCorrect();
        this.order = answer.getOrder();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean isCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public int compareTo(AnswerWrapper o) {
        if (this.getOrder() > o.getOrder())
            return 1;
        else if (this.getOrder() < o.getOrder())
                return -1;
        else
            return 0;
    }
}
