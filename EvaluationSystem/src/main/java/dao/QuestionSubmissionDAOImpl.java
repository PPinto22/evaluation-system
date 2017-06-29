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
import org.hibernate.Query;
import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionSubmissionDAOImpl implements QuestionSubmissionDAO {
	private static final org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(QuestionSubmissionDAOImpl.class);
	public QuestionSubmission loadQuestionSubmissionByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadQuestionSubmissionByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("loadQuestionSubmissionByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionSubmission getQuestionSubmissionByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getQuestionSubmissionByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("getQuestionSubmissionByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionSubmission loadQuestionSubmissionByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadQuestionSubmissionByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadQuestionSubmissionByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionSubmission getQuestionSubmissionByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getQuestionSubmissionByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("getQuestionSubmissionByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionSubmission loadQuestionSubmissionByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (QuestionSubmission) session.load(QuestionSubmission.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("loadQuestionSubmissionByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionSubmission getQuestionSubmissionByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (QuestionSubmission) session.get(QuestionSubmission.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("getQuestionSubmissionByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionSubmission loadQuestionSubmissionByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (QuestionSubmission) session.load(QuestionSubmission.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("loadQuestionSubmissionByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionSubmission getQuestionSubmissionByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (QuestionSubmission) session.get(QuestionSubmission.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("getQuestionSubmissionByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryQuestionSubmission(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryQuestionSubmission(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("queryQuestionSubmission(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryQuestionSubmission(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryQuestionSubmission(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("queryQuestionSubmission(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionSubmission[] listQuestionSubmissionByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listQuestionSubmissionByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("listQuestionSubmissionByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionSubmission[] listQuestionSubmissionByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listQuestionSubmissionByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("listQuestionSubmissionByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryQuestionSubmission(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.QuestionSubmission as model.QuestionSubmission");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listQuestionSubmissionByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryQuestionSubmission(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.QuestionSubmission as model.QuestionSubmission");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.QuestionSubmission", lockMode);
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listQuestionSubmissionByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionSubmission[] listQuestionSubmissionByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryQuestionSubmission(session, condition, orderBy);
			return (QuestionSubmission[]) list.toArray(new QuestionSubmission[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listQuestionSubmissionByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionSubmission[] listQuestionSubmissionByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			List list = queryQuestionSubmission(session, condition, orderBy, lockMode);
			return (QuestionSubmission[]) list.toArray(new QuestionSubmission[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listQuestionSubmissionByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionSubmission loadQuestionSubmissionByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadQuestionSubmissionByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("loadQuestionSubmissionByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionSubmission loadQuestionSubmissionByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadQuestionSubmissionByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadQuestionSubmissionByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionSubmission loadQuestionSubmissionByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		QuestionSubmission[] questionSubmissions = listQuestionSubmissionByQuery(session, condition, orderBy);
		if (questionSubmissions != null && questionSubmissions.length > 0)
			return questionSubmissions[0];
		else
			return null;
	}
	
	public QuestionSubmission loadQuestionSubmissionByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		QuestionSubmission[] questionSubmissions = listQuestionSubmissionByQuery(session, condition, orderBy, lockMode);
		if (questionSubmissions != null && questionSubmissions.length > 0)
			return questionSubmissions[0];
		else
			return null;
	}
	
	public java.util.Iterator iterateQuestionSubmissionByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateQuestionSubmissionByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("iterateQuestionSubmissionByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateQuestionSubmissionByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateQuestionSubmissionByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("iterateQuestionSubmissionByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateQuestionSubmissionByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.QuestionSubmission as model.QuestionSubmission");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateQuestionSubmissionByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateQuestionSubmissionByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.QuestionSubmission as model.QuestionSubmission");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.QuestionSubmission", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateQuestionSubmissionByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionSubmission createQuestionSubmission() {
		return new QuestionSubmission();
	}
	
	public boolean save(QuestionSubmission questionSubmission) throws PersistentException {
		try {
			ClassesPersistentManager.instance().saveObject(questionSubmission);
			return true;
		}
		catch (Exception e) {
			_logger.error("save(model.QuestionSubmission questionSubmission)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean delete(QuestionSubmission questionSubmission) throws PersistentException {
		try {
			ClassesPersistentManager.instance().deleteObject(questionSubmission);
			return true;
		}
		catch (Exception e) {
			_logger.error("delete(model.QuestionSubmission questionSubmission)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(QuestionSubmission questionSubmission)throws PersistentException {
		try {
			if (questionSubmission.get_answer() != null) {
				questionSubmission.get_answer()._questionSubmission.remove(questionSubmission);
			}
			
			if (questionSubmission.get_submission() != null) {
				questionSubmission.get_submission()._questionSubmissions.remove(questionSubmission);
			}
			
			return delete(questionSubmission);
		}
		catch(Exception e) {
			_logger.error("deleteAndDissociate()", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(QuestionSubmission questionSubmission, PersistentSession session)throws PersistentException {
		try {
			if (questionSubmission.get_answer() != null) {
				questionSubmission.get_answer()._questionSubmission.remove(questionSubmission);
			}
			
			if (questionSubmission.get_submission() != null) {
				questionSubmission.get_submission()._questionSubmissions.remove(questionSubmission);
			}
			
			try {
				session.delete(questionSubmission);
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
	
	public boolean refresh(QuestionSubmission questionSubmission) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().refresh(questionSubmission);
			return true;
		}
		catch (Exception e) {
			_logger.error("refresh(model.QuestionSubmission questionSubmission)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean evict(QuestionSubmission questionSubmission) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().evict(questionSubmission);
			return true;
		}
		catch (Exception e) {
			_logger.error("evict(model.QuestionSubmission questionSubmission)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionSubmission loadQuestionSubmissionByCriteria(QuestionSubmissionCriteria questionSubmissionCriteria) {
		QuestionSubmission[] questionSubmissions = listQuestionSubmissionByCriteria(questionSubmissionCriteria);
		if(questionSubmissions == null || questionSubmissions.length == 0) {
			return null;
		}
		return questionSubmissions[0];
	}
	
	public QuestionSubmission[] listQuestionSubmissionByCriteria(QuestionSubmissionCriteria questionSubmissionCriteria) {
		return questionSubmissionCriteria.listQuestionSubmission();
	}
}
