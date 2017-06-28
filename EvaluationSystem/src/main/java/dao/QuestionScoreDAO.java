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
import model.QuestionScore;
import org.orm.*;
import org.hibernate.LockMode;

public interface QuestionScoreDAO {
	boolean exists(int questionID, int examID) throws PersistentException;

	public QuestionScore loadQuestionScoreByORMID(int ID) throws PersistentException;
	public QuestionScore getQuestionScoreByORMID(int ID) throws PersistentException;
	public QuestionScore loadQuestionScoreByORMID(int ID, LockMode lockMode) throws PersistentException;
	public QuestionScore getQuestionScoreByORMID(int ID, LockMode lockMode) throws PersistentException;
	public QuestionScore loadQuestionScoreByORMID(PersistentSession session, int ID) throws PersistentException;
	public QuestionScore getQuestionScoreByORMID(PersistentSession session, int ID) throws PersistentException;
	public QuestionScore loadQuestionScoreByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public QuestionScore getQuestionScoreByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public QuestionScore[] listQuestionScoreByQuery(String condition, String orderBy) throws PersistentException;
	public QuestionScore[] listQuestionScoreByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryQuestionScore(String condition, String orderBy) throws PersistentException;
	public java.util.List queryQuestionScore(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateQuestionScoreByQuery(String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateQuestionScoreByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public QuestionScore[] listQuestionScoreByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public QuestionScore[] listQuestionScoreByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryQuestionScore(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.List queryQuestionScore(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateQuestionScoreByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateQuestionScoreByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public QuestionScore loadQuestionScoreByQuery(String condition, String orderBy) throws PersistentException;
	public QuestionScore loadQuestionScoreByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public QuestionScore loadQuestionScoreByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public QuestionScore loadQuestionScoreByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public QuestionScore createQuestionScore();
	public boolean save(QuestionScore questionScore) throws PersistentException;
	public boolean delete(QuestionScore questionScore) throws PersistentException;
	public boolean deleteAndDissociate(QuestionScore questionScore) throws PersistentException;
	public boolean deleteAndDissociate(QuestionScore questionScore, PersistentSession session) throws PersistentException;
	public boolean refresh(QuestionScore questionScore) throws PersistentException;
	public boolean evict(QuestionScore questionScore) throws PersistentException;
	public QuestionScore loadQuestionScoreByCriteria(QuestionScoreCriteria questionScoreCriteria);
	public QuestionScore[] listQuestionScoreByCriteria(QuestionScoreCriteria questionScoreCriteria);
}
