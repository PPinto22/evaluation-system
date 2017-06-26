package wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import model.persistent.Answer;
import model.persistent.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionWrapper {
    private int id;
    private String text;
    private String category;
    private int difficulty;
    private List<AnswerWrapper> answers;

    public QuestionWrapper(){}

    public QuestionWrapper(Question question, boolean hideCorrectAnswers){
        this.id = question.getID();
        this.text = question.getText();
        this.category = question.getCategory();
        this.difficulty = question.getDifficulty();
        this.answers = new ArrayList<>();
        for(Answer answer: question.getAnswers()){
            this.answers.add(new AnswerWrapper(answer,hideCorrectAnswers));
        }
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

    public List<AnswerWrapper> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerWrapper> answers) {
        this.answers = answers;
    }
}
