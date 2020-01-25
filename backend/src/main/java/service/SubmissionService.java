package service;

import exception.ExistentEntityException;
import exception.InvalidAnswerException;
import exception.InvalidQuestionException;
import exception.NonExistentEntityException;
import model.*;
import org.orm.PersistentException;
import org.orm.PersistentSession;

import java.util.Map;

public interface SubmissionService {

    Submission getSubmissionByID(PersistentSession session, int ID) throws PersistentException, NonExistentEntityException;
    Submission getSubmissionByStudentAndExam(PersistentSession session, Student student, Exam exam) throws PersistentException, NonExistentEntityException;
    QuestionSubmission getQuestionSubmission(Submission submission, Question question) throws NonExistentEntityException;
    boolean exists(PersistentSession session, int ID) throws PersistentException;
    boolean exists(PersistentSession session, Student student, Exam exam) throws PersistentException;
    boolean exists(PersistentSession session, Exam exam) throws PersistentException;

    Submission submit(PersistentSession session, Student student, Exam exam, Map<Question, Answer> answers) throws ExistentEntityException, PersistentException, InvalidQuestionException, InvalidAnswerException;
    Submission updateSubmission(PersistentSession session, Submission submission, Map<Question, Answer> answers) throws InvalidAnswerException, PersistentException, InvalidQuestionException;

    void deleteSubmission(Submission submission) throws PersistentException;
}
