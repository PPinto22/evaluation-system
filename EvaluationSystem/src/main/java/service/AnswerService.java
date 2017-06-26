package service;

import exception.ExistentEntityException;
import exception.NonExistentEntityException;
import model.persistent.Answer;
import org.orm.PersistentException;

public interface AnswerService {

    Answer getAnswerByID(int ID) throws NonExistentEntityException, PersistentException;
    boolean exists(int ID) throws PersistentException;
}
