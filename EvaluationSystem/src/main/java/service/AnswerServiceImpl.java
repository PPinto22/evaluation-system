package service;

import dao.AnswerDAO;
import exception.NonExistentEntityException;
import model.Answer;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

    AnswerDAO answerDAO;

    public AnswerServiceImpl(AnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
    }

    @Override
    public Answer getAnswerByID(PersistentSession session, int ID) throws NonExistentEntityException, PersistentException {
        if(!exists(session, ID))
            throw new NonExistentEntityException();

        return answerDAO.loadAnswerByORMID(session, ID);
    }

    @Override
    public boolean exists(PersistentSession session, int ID) throws PersistentException {
        return answerDAO.exists(session,ID);
    }
}
