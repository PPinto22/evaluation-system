package service;

import model.persistent.Exam;
import model.persistent.Question;
import org.orm.PersistentException;

public interface ExamService {

    boolean examContainsQuestion(Exam exam, Question question) throws PersistentException;
}
