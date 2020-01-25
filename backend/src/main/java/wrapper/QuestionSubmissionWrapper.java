package wrapper;


import com.fasterxml.jackson.annotation.JsonInclude;
import model.QuestionScore;
import model.QuestionSubmission;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionSubmissionWrapper {
    private QuestionScoreWrapper question;
    private AnswerWrapper answer;

    public QuestionSubmissionWrapper(){}

    public QuestionSubmissionWrapper(QuestionScore questionScore, boolean hideCorrect){
        this.question = new QuestionScoreWrapper(questionScore, hideCorrect);
        this.answer = null;
    }

    public QuestionSubmissionWrapper(QuestionSubmission question, boolean hideCorrect){
        this.question = new QuestionScoreWrapper(question.get_question(), hideCorrect);
        this.answer = new AnswerWrapper(question.get_answer(), hideCorrect);
    }

    public QuestionScoreWrapper getQuestion() {
        return question;
    }

    public void setQuestion(QuestionScoreWrapper question) {
        this.question = question;
    }

    public AnswerWrapper getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerWrapper answer) {
        this.answer = answer;
    }
}
