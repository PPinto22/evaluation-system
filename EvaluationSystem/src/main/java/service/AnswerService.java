package service;

import exception.NonExistentEntityException;
import model.Answer;
import org.orm.PersistentException;

public interface AnswerService {

    Answer getAnswerByID(int ID) throws NonExistentEntityException, PersistentException;
    boolean exists(int ID) throws PersistentException;
}
