package wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import model.QuestionScore;
import model.QuestionSubmission;
import model.Submission;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubmissionWrapper {
    private int id;
    private Float score;
    List<QuestionSubmissionWrapper> questions;

    public SubmissionWrapper(){}

    public SubmissionWrapper(Submission submission, boolean hideQuestions, boolean hideAnswers){
        this.id = submission.getID();
        if(hideQuestions) {
            this.questions = null;
            this.score = null;
        }
        else {
            float score = submission.getScore();
            this.score = score > 20 ? 20:score;
            this.questions = new ArrayList<>();
            Map<QuestionScore, QuestionSubmission> submitedMap = new HashMap<>();
            for (QuestionSubmission questionSubmission : submission._questionSubmissions.toArray()) {
                submitedMap.put(questionSubmission.get_question(), questionSubmission);
            }
            for (QuestionScore questionScore : submission.get_exam().getQuestionScores()) {
                if (submitedMap.containsKey(questionScore))
                    this.questions.add(new QuestionSubmissionWrapper(submitedMap.get(questionScore), hideAnswers));
                else
                    this.questions.add(new QuestionSubmissionWrapper(questionScore, hideAnswers));
            }
        }
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<QuestionSubmissionWrapper> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionSubmissionWrapper> questions) {
        this.questions = questions;
    }
}
