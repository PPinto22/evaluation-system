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
import model.persistent.QuestionSubmission;
import model.persistent.Submission;
import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubmissionDAOImpl implements SubmissionDAO {

	@Override
	public boolean exists(int ID) throws PersistentException {
		return this.getSubmissionByORMID(ID) != null;
	}

	@Override
	public boolean exists(int studentID, int examID) throws PersistentException {
		SubmissionCriteria criteria = new SubmissionCriteria();
		criteria._studentId.eq(studentID);
		criteria._examId.eq(examID);
		return this.loadSubmissionByCriteria(criteria) != null;
	}

	private static final org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(SubmissionDAOImpl.class);
	public Submission loadSubmissionByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadSubmissionByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("loadSubmissionByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Submission getSubmissionByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getSubmissionByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("getSubmissionByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Submission loadSubmissionByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadSubmissionByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadSubmissionByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Submission getSubmissionByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getSubmissionByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("getSubmissionByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Submission loadSubmissionByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Submission) session.load(Submission.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("loadSubmissionByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Submission getSubmissionByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Submission) session.get(Submission.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("getSubmissionByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Submission loadSubmissionByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (Submission) session.load(Submission.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("loadSubmissionByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Submission getSubmissionByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (Submission) session.get(Submission.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("getSubmissionByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public List querySubmission(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return querySubmission(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("querySubmission(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List querySubmission(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return querySubmission(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("querySubmission(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Submission[] listSubmissionByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listSubmissionByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("listSubmissionByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Submission[] listSubmissionByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listSubmissionByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("listSubmissionByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List querySubmission(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.persistent.Submission as model.persistent.Submission");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listSubmissionByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List querySubmission(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.persistent.Submission as model.persistent.Submission");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.persistent.Submission", lockMode);
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listSubmissionByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Submission[] listSubmissionByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = querySubmission(session, condition, orderBy);
			return (Submission[]) list.toArray(new Submission[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listSubmissionByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Submission[] listSubmissionByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			List list = querySubmission(session, condition, orderBy, lockMode);
			return (Submission[]) list.toArray(new Submission[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listSubmissionByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Submission loadSubmissionByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadSubmissionByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("loadSubmissionByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Submission loadSubmissionByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadSubmissionByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadSubmissionByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Submission loadSubmissionByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Submission[] submissions = listSubmissionByQuery(session, condition, orderBy);
		if (submissions != null && submissions.length > 0)
			return submissions[0];
		else
			return null;
	}
	
	public Submission loadSubmissionByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		Submission[] submissions = listSubmissionByQuery(session, condition, orderBy, lockMode);
		if (submissions != null && submissions.length > 0)
			return submissions[0];
		else
			return null;
	}
	
	public java.util.Iterator iterateSubmissionByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateSubmissionByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("iterateSubmissionByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateSubmissionByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateSubmissionByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("iterateSubmissionByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateSubmissionByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.persistent.Submission as model.persistent.Submission");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateSubmissionByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateSubmissionByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.persistent.Submission as model.persistent.Submission");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.persistent.Submission", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateSubmissionByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Submission createSubmission() {
		return new Submission();
	}
	
	public boolean save(Submission submission) throws PersistentException {
		try {
			ClassesPersistentManager.instance().saveObject(submission);
			return true;
		}
		catch (Exception e) {
			_logger.error("save(model.persistent.Submission submission)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean delete(Submission submission) throws PersistentException {
		try {
			ClassesPersistentManager.instance().deleteObject(submission);
			return true;
		}
		catch (Exception e) {
			_logger.error("delete(model.persistent.Submission submission)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(Submission submission)throws PersistentException {
		try {
			if (submission.get_exam() != null) {
				submission.get_exam()._submissions.remove(submission);
			}
			
			if (submission.get_student() != null) {
				submission.get_student()._submissions.remove(submission);
			}
			
			QuestionSubmission[] l_questionSubmissionss = submission._questionSubmissions.toArray();
			for(int i = 0; i < l_questionSubmissionss.length; i++) {
				l_questionSubmissionss[i].set_submission(null);
			}
			return delete(submission);
		}
		catch(Exception e) {
			_logger.error("deleteAndDissociate()", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(Submission submission, PersistentSession session)throws PersistentException {
		try {
			if (submission.get_exam() != null) {
				submission.get_exam()._submissions.remove(submission);
			}
			
			if (submission.get_student() != null) {
				submission.get_student()._submissions.remove(submission);
			}
			
			QuestionSubmission[] l_questionSubmissionss = submission._questionSubmissions.toArray();
			for(int i = 0; i < l_questionSubmissionss.length; i++) {
				l_questionSubmissionss[i].set_submission(null);
			}
			try {
				session.delete(submission);
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
	
	public boolean refresh(Submission submission) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().refresh(submission);
			return true;
		}
		catch (Exception e) {
			_logger.error("refresh(model.persistent.Submission submission)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean evict(Submission submission) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().evict(submission);
			return true;
		}
		catch (Exception e) {
			_logger.error("evict(model.persistent.Submission submission)", e);
			throw new PersistentException(e);
		}
	}
	
	public Submission loadSubmissionByCriteria(SubmissionCriteria submissionCriteria) {
		Submission[] submissions = listSubmissionByCriteria(submissionCriteria);
		if(submissions == null || submissions.length == 0) {
			return null;
		}
		return submissions[0];
	}
	
	public Submission[] listSubmissionByCriteria(SubmissionCriteria submissionCriteria) {
		return submissionCriteria.listSubmission();
	}
}
