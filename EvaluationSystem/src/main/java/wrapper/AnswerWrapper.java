package wrapper;

public class AnswerPOSTWrapper {
    private String text;
    private boolean correct;

    public AnswerPOSTWrapper(){}

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
