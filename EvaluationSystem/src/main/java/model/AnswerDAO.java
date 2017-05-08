package model; /**
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
import org.orm.*;
import org.hibernate.LockMode;

public interface AnswerDAO {
	public Answer loadAnswerByORMID(int ID) throws PersistentException;
	public Answer getAnswerByORMID(int ID) throws PersistentException;
	public Answer loadAnswerByORMID(int ID, LockMode lockMode) throws PersistentException;
	public Answer getAnswerByORMID(int ID, LockMode lockMode) throws PersistentException;
	public Answer loadAnswerByORMID(PersistentSession session, int ID) throws PersistentException;
	public Answer getAnswerByORMID(PersistentSession session, int ID) throws PersistentException;
	public Answer loadAnswerByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public Answer getAnswerByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public Answer[] listAnswerByQuery(String condition, String orderBy) throws PersistentException;
	public Answer[] listAnswerByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryAnswer(String condition, String orderBy) throws PersistentException;
	public java.util.List queryAnswer(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateAnswerByQuery(String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateAnswerByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Answer[] listAnswerByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public Answer[] listAnswerByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryAnswer(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.List queryAnswer(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateAnswerByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateAnswerByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Answer loadAnswerByQuery(String condition, String orderBy) throws PersistentException;
	public Answer loadAnswerByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Answer loadAnswerByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public Answer loadAnswerByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Answer createAnswer();
	public boolean save(Answer answer) throws PersistentException;
	public boolean delete(Answer answer) throws PersistentException;
	public boolean deleteAndDissociate(Answer answer) throws PersistentException;
	public boolean deleteAndDissociate(Answer answer, PersistentSession session) throws PersistentException;
	public boolean refresh(Answer answer) throws PersistentException;
	public boolean evict(Answer answer) throws PersistentException;
	public Answer loadAnswerByCriteria(AnswerCriteria answerCriteria);
	public Answer[] listAnswerByCriteria(AnswerCriteria answerCriteria);
}
