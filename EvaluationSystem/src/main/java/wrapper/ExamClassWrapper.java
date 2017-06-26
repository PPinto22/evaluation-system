package wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import model.persistent.Exam;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamClassWrapper extends ExamWrapper{

    private GroupClassWrapper group;

    public ExamClassWrapper() {}

    public ExamClassWrapper(Exam exam, boolean hideQuestions, boolean hideAnswers) {
        super(exam, hideQuestions, hideAnswers);
        this.group = new GroupClassWrapper(exam.get_group());
    }

    public GroupClassWrapper getGroup() {
        return group;
    }

    public void setGroup(GroupClassWrapper group) {
        this.group = group;
    }
}
