package dao; /**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: Universidade do Minho
 * License Type: Academic
 */
import model.Submission;
import org.orm.*;
import org.hibernate.LockMode;

public interface SubmissionDAO {
	boolean exists(int ID) throws PersistentException;
	boolean exists(int studentID, int examID) throws PersistentException;
	boolean existsExam(int examID) throws PersistentException;
	Submission loadSubmissionByStudentAndExam(int studentID, int examID) throws PersistentException;

	public Submission loadSubmissionByORMID(int ID) throws PersistentException;
	public Submission getSubmissionByORMID(int ID) throws PersistentException;
	public Submission loadSubmissionByORMID(int ID, LockMode lockMode) throws PersistentException;
	public Submission getSubmissionByORMID(int ID, LockMode lockMode) throws PersistentException;
	public Submission loadSubmissionByORMID(PersistentSession session, int ID) throws PersistentException;
	public Submission getSubmissionByORMID(PersistentSession session, int ID) throws PersistentException;
	public Submission loadSubmissionByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public Submission getSubmissionByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public Submission[] listSubmissionByQuery(String condition, String orderBy) throws PersistentException;
	public Submission[] listSubmissionByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List querySubmission(String condition, String orderBy) throws PersistentException;
	public java.util.List querySubmission(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateSubmissionByQuery(String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateSubmissionByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Submission[] listSubmissionByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public Submission[] listSubmissionByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List querySubmission(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.List querySubmission(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateSubmissionByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateSubmissionByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Submission loadSubmissionByQuery(String condition, String orderBy) throws PersistentException;
	public Submission loadSubmissionByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Submission loadSubmissionByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public Submission loadSubmissionByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Submission createSubmission();
	public boolean save(Submission submission) throws PersistentException;
	public boolean delete(Submission submission) throws PersistentException;
	public boolean deleteAndDissociate(Submission submission) throws PersistentException;
	public boolean deleteAndDissociate(Submission submission, PersistentSession session) throws PersistentException;
	public boolean refresh(Submission submission) throws PersistentException;
	public boolean evict(Submission submission) throws PersistentException;
	public Submission loadSubmissionByCriteria(SubmissionCriteria submissionCriteria);
	public Submission[] listSubmissionByCriteria(SubmissionCriteria submissionCriteria);
}
