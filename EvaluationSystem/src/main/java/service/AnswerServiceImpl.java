package service;

import dao.AnswerDAO;
import exception.NonExistentEntityException;
import model.Answer;
import org.orm.PersistentException;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {

    AnswerDAO answerDAO;

    public AnswerServiceImpl(AnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
    }

    @Override
    public Answer getAnswerByID(int ID) throws NonExistentEntityException, PersistentException {
        if(!exists(ID))
            throw new NonExistentEntityException();

        return answerDAO.loadAnswerByORMID(ID);
    }

    @Override
    public boolean exists(int ID) throws PersistentException {
        return answerDAO.exists(ID);
    }
}
