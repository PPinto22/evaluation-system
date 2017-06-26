package wrapper;

import model.persistent.Exam;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ExamsWrapper {

    private Map<String, Set<ExamWrapper>> exams;

    public ExamsWrapper(){}

    public ExamsWrapper(Map<String, Set<Exam>> exams, boolean includeClass) {
        this.exams = new TreeMap<>();
        for(String key: exams.keySet()){
            this.exams.put(key, new TreeSet<>());
            for(Exam exam: exams.get(key)){
                ExamWrapper examWrapper = includeClass ?
                        new ExamClassWrapper(exam, true, true) :
                        new ExamWrapper(exam, true, true);
                this.exams.get(key).add(examWrapper);
            }
        }
    }

    public Map<String, Set<ExamWrapper>> getExams() {
        return exams;
    }

    public void setExams(Map<String, Set<ExamWrapper>> exams) {
        this.exams = exams;
    }
}
