package wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import model.persistent.QuestionScore;
import model.persistent.QuestionSubmission;
import model.persistent.Submission;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubmissionWrapper {
    private int id;
    List<QuestionSubmissionWrapper> questions;

    public SubmissionWrapper(){}

    public SubmissionWrapper(Submission submission, boolean hideQuestions, boolean hideAnswers){
        this.id = submission.getID();
        if(hideQuestions)
            this.questions = null;
        else {
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
