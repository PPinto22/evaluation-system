package wrapper;

import java.util.List;

public class ExamPOSTWrapper {

    // unix time
    private long beginDate;
    // minutos
    private int duration;
    private String name;
    // question IDs
    private List<Integer> questionIDs;

    public ExamPOSTWrapper(){}

    public ExamPOSTWrapper(long beginDate, int duration, String name, List<Integer> questionIDs) {
        this.beginDate = beginDate;
        this.duration = duration;
        this.name = name;
        this.questionIDs = questionIDs;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getQuestionIDs() {
        return questionIDs;
    }

    public void setQuestionIDs(List<Integer> questionIDs) {
        this.questionIDs = questionIDs;
    }
}
