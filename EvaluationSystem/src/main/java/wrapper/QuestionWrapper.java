package wrapper;

public class QuestionPOSTWrapper {
    private String text;
    private String category;
    private int difficulty;
    private AnswerPOSTWrapper[] answers;

    public QuestionPOSTWrapper(){}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public AnswerPOSTWrapper[] getAnswers() {
        return answers;
    }

    public void setAnswers(AnswerPOSTWrapper[] answers) {
        this.answers = answers;
    }
}
