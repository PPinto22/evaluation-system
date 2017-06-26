package service;

import exception.*;
import model.persistent.Exam;
import model.persistent.Group;
import model.persistent.Question;
import org.orm.PersistentException;

import java.util.List;

public interface ExamService {

    boolean exists(int examID) throws PersistentException;
    boolean exists(Group group, String examName) throws PersistentException;
    boolean examContainsQuestion(Exam exam, Question question) throws PersistentException;
    boolean examHasStarted(Exam exam);
    boolean examHasFinished(Exam exam);
    Exam getExamByID(int examID) throws PersistentException, NonExistentEntityException;
    Exam addExamToGroup(Group group, Exam exam) throws PersistentException, ExistentEntityException;
    Exam createExam(String name, int minutes, long beginDate, List<Integer> questionIDs, Group group)
            throws InvalidExamException, PersistentException, InvalidQuestionException, ExistentEntityException;
}
