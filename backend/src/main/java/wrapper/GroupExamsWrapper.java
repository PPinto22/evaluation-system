package wrapper;


import model.Exam;
import model.Group;
import model.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupExamsWrapper {
    private GroupClassWrapper group;
    private List<ExamScoreWrapper> exams;

    public GroupExamsWrapper(){}

    public GroupExamsWrapper(Group group, Map<Exam, Score> scoreMap){
        this.group = new GroupClassWrapper(group);
        this.exams = new ArrayList<>();
        for(Exam exam: scoreMap.keySet()){
            Score score = scoreMap.get(exam);
            this.exams.add(new ExamScoreWrapper(exam,score,true));
        }
    }

    public GroupClassWrapper getGroup() {
        return group;
    }

    public void setGroup(GroupClassWrapper group) {
        this.group = group;
    }

    public List<ExamScoreWrapper> getExams() {
        return exams;
    }

    public void setExams(List<ExamScoreWrapper> exams) {
        this.exams = exams;
    }
}
