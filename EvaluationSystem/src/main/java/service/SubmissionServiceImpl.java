package service;

import dao.SubmissionDAO;
import exception.NonExistentEntityException;
import model.persistent.Exam;
import model.persistent.Student;
import model.persistent.Submission;
import org.orm.PersistentException;
import org.springframework.stereotype.Service;

@Service
public class SubmissionServiceImpl implements SubmissionService{

    SubmissionDAO submissionDAO;

    public SubmissionServiceImpl(SubmissionDAO submissionDAO) {
        this.submissionDAO = submissionDAO;
    }

    @Override
    public Submission getSubmissionByID(int ID) throws PersistentException, NonExistentEntityException {
        if(!submissionDAO.exists(ID))
            throw new NonExistentEntityException();
        return submissionDAO.loadSubmissionByORMID(ID);
    }

    @Override
    public boolean exists(int ID) throws PersistentException {
        return submissionDAO.exists(ID);
    }

    @Override
    public boolean exists(Student student, Exam exam) throws PersistentException {
        return submissionDAO.exists(student.getID(), exam.getID());
    }
}
