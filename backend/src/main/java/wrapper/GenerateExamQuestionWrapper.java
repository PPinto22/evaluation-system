package wrapper;

import java.util.List;

public class GenerateExamQuestionWrapper {
    private String category;
    private int difficulty;
    private List<Integer> excluded;

    public GenerateExamQuestionWrapper(){}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public List<Integer> getExcluded() {
        return excluded;
    }

    public void setExcluded(List<Integer> excluded) {
        this.excluded = excluded;
    }
}
