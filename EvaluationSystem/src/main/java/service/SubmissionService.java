package service;

import exception.NonExistentEntityException;
import model.persistent.Exam;
import model.persistent.Student;
import model.persistent.Submission;
import org.orm.PersistentException;

public interface SubmissionService {

    Submission getSubmissionByID(int ID) throws PersistentException, NonExistentEntityException;
    boolean exists(int ID) throws PersistentException;
    boolean exists(Student student, Exam exam) throws PersistentException;
}
