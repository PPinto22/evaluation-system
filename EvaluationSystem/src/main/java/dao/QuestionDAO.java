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
import model.Question;
import org.orm.*;
import org.hibernate.LockMode;

import java.util.List;

public interface QuestionDAO {
	boolean exists(int questionID) throws PersistentException;
	boolean exists(int classID, String text) throws PersistentException;
	List<Question> listQuestionsByClassAndText(int classID, String text) throws PersistentException;

	public Question loadQuestionByORMID(int ID) throws PersistentException;
	public Question getQuestionByORMID(int ID) throws PersistentException;
	public Question loadQuestionByORMID(int ID, LockMode lockMode) throws PersistentException;
	public Question getQuestionByORMID(int ID, LockMode lockMode) throws PersistentException;
	public Question loadQuestionByORMID(PersistentSession session, int ID) throws PersistentException;
	public Question getQuestionByORMID(PersistentSession session, int ID) throws PersistentException;
	public Question loadQuestionByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public Question getQuestionByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public Question[] listQuestionByQuery(String condition, String orderBy) throws PersistentException;
	public Question[] listQuestionByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryQuestion(String condition, String orderBy) throws PersistentException;
	public java.util.List queryQuestion(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateQuestionByQuery(String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateQuestionByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Question[] listQuestionByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public Question[] listQuestionByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryQuestion(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.List queryQuestion(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateQuestionByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateQuestionByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Question loadQuestionByQuery(String condition, String orderBy) throws PersistentException;
	public Question loadQuestionByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Question loadQuestionByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public Question loadQuestionByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Question createQuestion();
	public boolean save(Question question) throws PersistentException;
	public boolean delete(Question question) throws PersistentException;
	public boolean deleteAndDissociate(Question question) throws PersistentException;
	public boolean deleteAndDissociate(Question question, PersistentSession session) throws PersistentException;
	public boolean refresh(Question question) throws PersistentException;
	public boolean evict(Question question) throws PersistentException;
	public Question loadQuestionByCriteria(QuestionCriteria questionCriteria);
	public Question[] listQuestionByCriteria(QuestionCriteria questionCriteria);
}
