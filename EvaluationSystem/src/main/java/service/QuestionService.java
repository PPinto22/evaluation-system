package service;

import exception.NonExistentEntityException;
import model.Answer;
import model.Question;
import model.Class;
import org.orm.PersistentException;

public interface QuestionService {
    Question getQuestionByID(int ID) throws PersistentException, NonExistentEntityException;
    boolean questionContainsAnswer(Question question, Answer answer);
    boolean exists(int ID) throws PersistentException;
    boolean exists(Class cl, Question question) throws PersistentException;
    boolean validate(Question question);
}