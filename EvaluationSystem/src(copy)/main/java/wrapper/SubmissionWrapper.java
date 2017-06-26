package wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubmissionWrapper {
    private ExamWrapper exam;
//    private //TODO
}
