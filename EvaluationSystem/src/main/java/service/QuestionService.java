package service;

import exception.ExistentEntityException;
import exception.InvalidQuestionException;
import exception.NonExistentEntityException;
import model.Answer;
import model.Question;
import model.Class;
import org.orm.PersistentException;

import java.util.List;

public interface QuestionService {
    List<Question> listQuestionsByIDs(List<Integer> IDs) throws PersistentException, NonExistentEntityException;
    Question getQuestionByID(int ID) throws PersistentException, NonExistentEntityException;
    boolean questionInUse(Question question) throws PersistentException;
    boolean questionContainsAnswer(Question question, Answer answer);
    boolean exists(int ID) throws PersistentException;
    boolean exists(Class cl, Question question) throws PersistentException;
    boolean validate(Question question);

    Question updateQuestion(Question question, String text, String category, Integer difficulty, List<Answer> answers) throws InvalidQuestionException, PersistentException, ExistentEntityException;
}
