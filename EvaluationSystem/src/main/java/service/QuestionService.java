package service;

import exception.EntityNotRemovableException;
import exception.ExistentEntityException;
import exception.InvalidQuestionException;
import exception.NonExistentEntityException;
import model.Answer;
import model.Question;
import model.Class;
import org.orm.PersistentException;
import org.orm.PersistentSession;

import java.util.List;

public interface QuestionService {
    List<Question> listQuestionsByIDs(PersistentSession session, List<Integer> IDs) throws PersistentException, NonExistentEntityException;
    Question getQuestionByID(PersistentSession session, int ID) throws PersistentException, NonExistentEntityException;
    boolean questionInUse(PersistentSession session, Question question) throws PersistentException;
    boolean questionContainsAnswer(Question question, Answer answer);
    boolean exists(PersistentSession session, int ID) throws PersistentException;
    boolean exists(PersistentSession session, Class cl, Question question) throws PersistentException;
    boolean validate(Question question);
    void delete(PersistentSession session, Question question) throws PersistentException, EntityNotRemovableException;

    Question updateQuestion(PersistentSession session, Question question, String text, String category, Integer difficulty, List<Answer> answers) throws InvalidQuestionException, PersistentException, ExistentEntityException;
}
