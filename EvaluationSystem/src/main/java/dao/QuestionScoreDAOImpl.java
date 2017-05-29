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
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class QuestionScoreDAOImpl implements QuestionScoreDAO {
	private static final org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(QuestionScoreDAOImpl.class);
	public QuestionScore loadQuestionScoreByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadQuestionScoreByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("loadQuestionScoreByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionScore getQuestionScoreByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getQuestionScoreByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("getQuestionScoreByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionScore loadQuestionScoreByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadQuestionScoreByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadQuestionScoreByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionScore getQuestionScoreByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getQuestionScoreByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("getQuestionScoreByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionScore loadQuestionScoreByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (QuestionScore) session.load(QuestionScore.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("loadQuestionScoreByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionScore getQuestionScoreByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (QuestionScore) session.get(QuestionScore.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("getQuestionScoreByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionScore loadQuestionScoreByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (QuestionScore) session.load(QuestionScore.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("loadQuestionScoreByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionScore getQuestionScoreByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (QuestionScore) session.get(QuestionScore.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("getQuestionScoreByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryQuestionScore(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryQuestionScore(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("queryQuestionScore(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryQuestionScore(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryQuestionScore(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("queryQuestionScore(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionScore[] listQuestionScoreByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listQuestionScoreByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("listQuestionScoreByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionScore[] listQuestionScoreByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listQuestionScoreByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("listQuestionScoreByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryQuestionScore(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.QuestionScore as model.QuestionScore");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listQuestionScoreByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryQuestionScore(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.QuestionScore as model.QuestionScore");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.QuestionScore", lockMode);
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listQuestionScoreByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionScore[] listQuestionScoreByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryQuestionScore(session, condition, orderBy);
			return (QuestionScore[]) list.toArray(new QuestionScore[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listQuestionScoreByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionScore[] listQuestionScoreByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			List list = queryQuestionScore(session, condition, orderBy, lockMode);
			return (QuestionScore[]) list.toArray(new QuestionScore[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listQuestionScoreByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionScore loadQuestionScoreByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadQuestionScoreByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("loadQuestionScoreByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionScore loadQuestionScoreByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadQuestionScoreByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadQuestionScoreByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionScore loadQuestionScoreByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		QuestionScore[] questionScores = listQuestionScoreByQuery(session, condition, orderBy);
		if (questionScores != null && questionScores.length > 0)
			return questionScores[0];
		else
			return null;
	}
	
	public QuestionScore loadQuestionScoreByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		QuestionScore[] questionScores = listQuestionScoreByQuery(session, condition, orderBy, lockMode);
		if (questionScores != null && questionScores.length > 0)
			return questionScores[0];
		else
			return null;
	}
	
	public java.util.Iterator iterateQuestionScoreByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateQuestionScoreByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("iterateQuestionScoreByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateQuestionScoreByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateQuestionScoreByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("iterateQuestionScoreByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateQuestionScoreByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.QuestionScore as model.QuestionScore");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateQuestionScoreByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateQuestionScoreByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.QuestionScore as model.QuestionScore");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.QuestionScore", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateQuestionScoreByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionScore createQuestionScore() {
		return new QuestionScore();
	}
	
	public boolean save(QuestionScore questionScore) throws PersistentException {
		try {
			ClassesPersistentManager.instance().saveObject(questionScore);
			return true;
		}
		catch (Exception e) {
			_logger.error("save(model.QuestionScore questionScore)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean delete(QuestionScore questionScore) throws PersistentException {
		try {
			ClassesPersistentManager.instance().deleteObject(questionScore);
			return true;
		}
		catch (Exception e) {
			_logger.error("delete(model.QuestionScore questionScore)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean refresh(QuestionScore questionScore) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().refresh(questionScore);
			return true;
		}
		catch (Exception e) {
			_logger.error("refresh(model.QuestionScore questionScore)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean evict(QuestionScore questionScore) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().evict(questionScore);
			return true;
		}
		catch (Exception e) {
			_logger.error("evict(model.QuestionScore questionScore)", e);
			throw new PersistentException(e);
		}
	}
	
	public QuestionScore loadQuestionScoreByCriteria(QuestionScoreCriteria questionScoreCriteria) {
		QuestionScore[] questionScores = listQuestionScoreByCriteria(questionScoreCriteria);
		if(questionScores == null || questionScores.length == 0) {
			return null;
		}
		return questionScores[0];
	}
	
	public QuestionScore[] listQuestionScoreByCriteria(QuestionScoreCriteria questionScoreCriteria) {
		return questionScoreCriteria.listQuestionScore();
	}
}
