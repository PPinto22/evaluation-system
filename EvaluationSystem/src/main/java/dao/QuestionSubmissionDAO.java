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
import model.QuestionSubmission;
import org.orm.*;
import org.hibernate.LockMode;

public interface QuestionSubmissionDAO {
	public QuestionSubmission loadQuestionSubmissionByORMID(int ID) throws PersistentException;
	public QuestionSubmission getQuestionSubmissionByORMID(int ID) throws PersistentException;
	public QuestionSubmission loadQuestionSubmissionByORMID(int ID, LockMode lockMode) throws PersistentException;
	public QuestionSubmission getQuestionSubmissionByORMID(int ID, LockMode lockMode) throws PersistentException;
	public QuestionSubmission loadQuestionSubmissionByORMID(PersistentSession session, int ID) throws PersistentException;
	public QuestionSubmission getQuestionSubmissionByORMID(PersistentSession session, int ID) throws PersistentException;
	public QuestionSubmission loadQuestionSubmissionByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public QuestionSubmission getQuestionSubmissionByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public QuestionSubmission[] listQuestionSubmissionByQuery(String condition, String orderBy) throws PersistentException;
	public QuestionSubmission[] listQuestionSubmissionByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryQuestionSubmission(String condition, String orderBy) throws PersistentException;
	public java.util.List queryQuestionSubmission(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateQuestionSubmissionByQuery(String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateQuestionSubmissionByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public QuestionSubmission[] listQuestionSubmissionByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public QuestionSubmission[] listQuestionSubmissionByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryQuestionSubmission(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.List queryQuestionSubmission(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateQuestionSubmissionByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateQuestionSubmissionByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public QuestionSubmission loadQuestionSubmissionByQuery(String condition, String orderBy) throws PersistentException;
	public QuestionSubmission loadQuestionSubmissionByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public QuestionSubmission loadQuestionSubmissionByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public QuestionSubmission loadQuestionSubmissionByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public QuestionSubmission createQuestionSubmission();
	public boolean save(QuestionSubmission questionSubmission) throws PersistentException;
	public boolean delete(QuestionSubmission questionSubmission) throws PersistentException;
	public boolean deleteAndDissociate(QuestionSubmission questionSubmission) throws PersistentException;
	public boolean deleteAndDissociate(QuestionSubmission questionSubmission, PersistentSession session) throws PersistentException;
	public boolean refresh(QuestionSubmission questionSubmission) throws PersistentException;
	public boolean evict(QuestionSubmission questionSubmission) throws PersistentException;
	public QuestionSubmission loadQuestionSubmissionByCriteria(QuestionSubmissionCriteria questionSubmissionCriteria);
	public QuestionSubmission[] listQuestionSubmissionByCriteria(QuestionSubmissionCriteria questionSubmissionCriteria);
}
