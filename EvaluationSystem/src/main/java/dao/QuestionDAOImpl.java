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
import org.hibernate.Query;
import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class QuestionDAOImpl implements QuestionDAO {
	@Override
	public List<Question> listQuestionsByClassCategoryAndDifficulty(PersistentSession session, int classID, String category, int difficulty) throws PersistentException {
		QuestionCriteria criteria = new QuestionCriteria(session);
		criteria._classId.eq(classID);
		criteria.category.eq(category);
		criteria.difficulty.eq(difficulty);
		return Arrays.asList( listQuestionByCriteria(criteria) );
	}

	@Override
	public boolean exists(PersistentSession session, int questionID) throws PersistentException {
		return this.getQuestionByORMID(session, questionID) != null;
	}

	@Override
	public boolean exists(PersistentSession session, int classID, String text) throws PersistentException {
		return !this.listQuestionsByClassAndText(session, classID,text).isEmpty();
	}

	@Override
	public List<Question> listQuestionsByClassAndText(PersistentSession session, int classID, String text) throws PersistentException {
		QuestionCriteria criteria = new QuestionCriteria(session);
		criteria._classId.eq(classID);
		criteria.text.eq(text);
		return Arrays.asList(this.listQuestionByCriteria(criteria));
	}

	@Override
	public List<Question> listQuestionsByClass(PersistentSession session, int classID) throws PersistentException {
		QuestionCriteria criteria = new QuestionCriteria(session);
		criteria._classId.eq(classID);
		return Arrays.asList(this.listQuestionByCriteria(criteria));
	}

	private static final org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(QuestionDAOImpl.class);
	public Question loadQuestionByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadQuestionByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("loadQuestionByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Question getQuestionByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getQuestionByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("getQuestionByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Question loadQuestionByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadQuestionByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadQuestionByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Question getQuestionByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getQuestionByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("getQuestionByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Question loadQuestionByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Question) session.load(Question.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("loadQuestionByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Question getQuestionByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Question) session.get(Question.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("getQuestionByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Question loadQuestionByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (Question) session.load(Question.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("loadQuestionByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Question getQuestionByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (Question) session.get(Question.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("getQuestionByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryQuestion(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryQuestion(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("queryQuestion(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryQuestion(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryQuestion(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("queryQuestion(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Question[] listQuestionByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listQuestionByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("listQuestionByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Question[] listQuestionByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listQuestionByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("listQuestionByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryQuestion(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.Question as model.Question");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listQuestionByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryQuestion(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.Question as model.Question");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.Question", lockMode);
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listQuestionByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Question[] listQuestionByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryQuestion(session, condition, orderBy);
			return (Question[]) list.toArray(new Question[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listQuestionByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Question[] listQuestionByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			List list = queryQuestion(session, condition, orderBy, lockMode);
			return (Question[]) list.toArray(new Question[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listQuestionByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Question loadQuestionByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadQuestionByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("loadQuestionByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Question loadQuestionByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadQuestionByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadQuestionByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Question loadQuestionByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Question[] questions = listQuestionByQuery(session, condition, orderBy);
		if (questions != null && questions.length > 0)
			return questions[0];
		else
			return null;
	}
	
	public Question loadQuestionByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		Question[] questions = listQuestionByQuery(session, condition, orderBy, lockMode);
		if (questions != null && questions.length > 0)
			return questions[0];
		else
			return null;
	}
	
	public java.util.Iterator iterateQuestionByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateQuestionByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("iterateQuestionByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateQuestionByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateQuestionByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("iterateQuestionByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateQuestionByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.Question as model.Question");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateQuestionByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateQuestionByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.Question as model.Question");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.Question", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateQuestionByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Question createQuestion() {
		return new Question();
	}
	
	public boolean save(Question question) throws PersistentException {
		try {
			ClassesPersistentManager.instance().saveObject(question);
			return true;
		}
		catch (Exception e) {
			_logger.error("save(model.Question question)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean delete(Question question) throws PersistentException {
		try {
			ClassesPersistentManager.instance().deleteObject(question);
			return true;
		}
		catch (Exception e) {
			_logger.error("delete(model.Question question)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(Question question)throws PersistentException {
		try {
			if (question.get_class() != null) {
				question.get_class()._question.remove(question);
			}
			
			return delete(question);
		}
		catch(Exception e) {
			_logger.error("deleteAndDissociate()", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(Question question, PersistentSession session)throws PersistentException {
		try {
			if (question.get_class() != null) {
				question.get_class()._question.remove(question);
			}
			
			try {
				session.delete(question);
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
	
	public boolean refresh(Question question) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().refresh(question);
			return true;
		}
		catch (Exception e) {
			_logger.error("refresh(model.Question question)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean evict(Question question) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().evict(question);
			return true;
		}
		catch (Exception e) {
			_logger.error("evict(model.Question question)", e);
			throw new PersistentException(e);
		}
	}
	
	public Question loadQuestionByCriteria(QuestionCriteria questionCriteria) {
		Question[] questions = listQuestionByCriteria(questionCriteria);
		if(questions == null || questions.length == 0) {
			return null;
		}
		return questions[0];
	}
	
	public Question[] listQuestionByCriteria(QuestionCriteria questionCriteria) {
		return questionCriteria.listQuestion();
	}
}
