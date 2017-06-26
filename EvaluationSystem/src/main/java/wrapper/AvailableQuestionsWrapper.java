package wrapper;

import model.persistent.Question;

import java.util.ArrayList;
import java.util.List;

public class AvailableQuestionsWrapper {

    private int available;
    private List<Integer> questionIDs;

    public AvailableQuestionsWrapper(){}

    public AvailableQuestionsWrapper(List<Question> questions){
        this.questionIDs = new ArrayList<>();
        for(Question q: questions)
            questionIDs.add(q.getID());
        this.available = this.questionIDs.size();
    }

    public int getAvailable() {
        return available;
    }

    public List<Integer> getQuestionIDs() {
        return questionIDs;
    }
}
