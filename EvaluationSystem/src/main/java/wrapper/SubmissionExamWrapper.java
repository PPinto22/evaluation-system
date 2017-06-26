package wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import model.persistent.Submission;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubmissionExamWrapper extends SubmissionWrapper{
    private ExamWrapper exam;

    public SubmissionExamWrapper(){
        super();
    }

    public SubmissionExamWrapper(Submission submission, boolean hideQuestions, boolean hideAnswers){
        super(submission, hideQuestions, hideAnswers);
        this.exam = new ExamClassWrapper(submission.get_exam(), true, true);
    }

    public ExamWrapper getExam() {
        return exam;
    }

    public void setExam(ExamWrapper exam) {
        this.exam = exam;
    }
}
