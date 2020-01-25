package wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import model.Exam;
import model.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamsScoresWrapper {

    private List<ExamScoreWrapper> exams;

    public ExamsScoresWrapper(){}

    public ExamsScoresWrapper(Map<Exam, Score> scoreMap){
        this.exams = new ArrayList<>();
        for(Exam exam: scoreMap.keySet()){
            Score score = scoreMap.get(exam);
            this.exams.add(new ExamScoreWrapper(exam,score,true));
        }
    }

    public List<ExamScoreWrapper> getExams() {
        return exams;
    }

    public void setExams(List<ExamScoreWrapper> exams) {
        this.exams = exams;
    }
}
