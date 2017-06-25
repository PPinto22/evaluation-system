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
import model.persistent.Answer;
import model.persistent.QuestionSubmission;
import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnswerDAOImpl implements AnswerDAO {
	private static final org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(AnswerDAOImpl.class);
	public Answer loadAnswerByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadAnswerByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("loadAnswerByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Answer getAnswerByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getAnswerByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("getAnswerByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Answer loadAnswerByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadAnswerByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadAnswerByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Answer getAnswerByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getAnswerByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("getAnswerByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Answer loadAnswerByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Answer) session.load(Answer.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("loadAnswerByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Answer getAnswerByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Answer) session.get(Answer.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("getAnswerByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Answer loadAnswerByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (Answer) session.load(Answer.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("loadAnswerByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Answer getAnswerByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (Answer) session.get(Answer.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("getAnswerByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryAnswer(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryAnswer(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("queryAnswer(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryAnswer(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryAnswer(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("queryAnswer(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Answer[] listAnswerByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listAnswerByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("listAnswerByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Answer[] listAnswerByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listAnswerByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("listAnswerByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryAnswer(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.persistent.Answer as model.persistent.Answer");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listAnswerByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryAnswer(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.persistent.Answer as model.persistent.Answer");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.persistent.Answer", lockMode);
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listAnswerByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Answer[] listAnswerByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryAnswer(session, condition, orderBy);
			return (Answer[]) list.toArray(new Answer[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listAnswerByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Answer[] listAnswerByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			List list = queryAnswer(session, condition, orderBy, lockMode);
			return (Answer[]) list.toArray(new Answer[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listAnswerByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Answer loadAnswerByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadAnswerByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("loadAnswerByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Answer loadAnswerByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadAnswerByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadAnswerByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Answer loadAnswerByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Answer[] answers = listAnswerByQuery(session, condition, orderBy);
		if (answers != null && answers.length > 0)
			return answers[0];
		else
			return null;
	}
	
	public Answer loadAnswerByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		Answer[] answers = listAnswerByQuery(session, condition, orderBy, lockMode);
		if (answers != null && answers.length > 0)
			return answers[0];
		else
			return null;
	}
	
	public java.util.Iterator iterateAnswerByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateAnswerByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("iterateAnswerByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateAnswerByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateAnswerByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("iterateAnswerByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateAnswerByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.persistent.Answer as model.persistent.Answer");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateAnswerByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateAnswerByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.persistent.Answer as model.persistent.Answer");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.persistent.Answer", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateAnswerByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Answer createAnswer() {
		return new Answer();
	}
	
	public boolean save(Answer answer) throws PersistentException {
		try {
			ClassesPersistentManager.instance().saveObject(answer);
			return true;
		}
		catch (Exception e) {
			_logger.error("save(model.persistent.Answer answer)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean delete(Answer answer) throws PersistentException {
		try {
			ClassesPersistentManager.instance().deleteObject(answer);
			return true;
		}
		catch (Exception e) {
			_logger.error("delete(model.persistent.Answer answer)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(Answer answer)throws PersistentException {
		try {
			QuestionSubmission[] l_questionSubmissions = answer._questionSubmission.toArray();
			for(int i = 0; i < l_questionSubmissions.length; i++) {
				l_questionSubmissions[i].set_answer(null);
			}
			return delete(answer);
		}
		catch(Exception e) {
			_logger.error("deleteAndDissociate()", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(Answer answer, PersistentSession session)throws PersistentException {
		try {
			QuestionSubmission[] l_questionSubmissions = answer._questionSubmission.toArray();
			for(int i = 0; i < l_questionSubmissions.length; i++) {
				l_questionSubmissions[i].set_answer(null);
			}
			try {
				session.delete(answer);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			_logger.error("deleteAndDissociate()", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean refresh(Answer answer) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().refresh(answer);
			return true;
		}
		catch (Exception e) {
			_logger.error("refresh(model.persistent.Answer answer)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean evict(Answer answer) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().evict(answer);
			return true;
		}
		catch (Exception e) {
			_logger.error("evict(model.persistent.Answer answer)", e);
			throw new PersistentException(e);
		}
	}
	
	public Answer loadAnswerByCriteria(AnswerCriteria answerCriteria) {
		Answer[] answers = listAnswerByCriteria(answerCriteria);
		if(answers == null || answers.length == 0) {
			return null;
		}
		return answers[0];
	}
	
	public Answer[] listAnswerByCriteria(AnswerCriteria answerCriteria) {
		return answerCriteria.listAnswer();
	}
}
