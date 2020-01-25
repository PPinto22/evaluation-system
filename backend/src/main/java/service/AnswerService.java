package service;

import exception.NonExistentEntityException;
import model.Answer;
import org.orm.PersistentException;
import org.orm.PersistentSession;

public interface AnswerService {

    Answer getAnswerByID(PersistentSession session, int ID) throws NonExistentEntityException, PersistentException;
    boolean exists(PersistentSession session, int ID) throws PersistentException;
}
