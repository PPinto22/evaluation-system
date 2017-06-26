package wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import model.persistent.Exam;
import model.persistent.QuestionScore;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamWrapper {

    private int id;
    private String name;
    private long beginDate;
    private int duration;
    private List<QuestionScoreWrapper> questions;

    public ExamWrapper(){}

    public ExamWrapper(Exam exam, boolean hideQuestions, boolean hideAnswers){
        this.id = exam.getID();
        this.name = exam.getName();
        this.beginDate = exam.getBeginDate();
        this.duration = exam.getDuration();
        if(!hideQuestions) {
            this.questions = new ArrayList<>();
            for (QuestionScore qs : exam.getQuestionScores()) {
                this.questions.add(new QuestionScoreWrapper(qs, false));
            }
        }
        else this.questions=null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(long beginDate) {
        this.beginDate = beginDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<QuestionScoreWrapper> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionScoreWrapper> questions) {
        this.questions = questions;
    }
}
