package wrapper;

import model.persistent.Answer;

public class AnswerWrapper {

    private int id;
    private String text;
    private boolean correct;

    public AnswerWrapper(){}

    public AnswerWrapper(Answer answer){
        this.id = answer.getID();
        this.text = answer.getText();
        this.correct = answer.isCorrect();
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

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
