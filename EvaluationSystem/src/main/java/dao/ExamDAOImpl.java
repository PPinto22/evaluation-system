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
import model.Exam;
import model.Submission;
import org.orm.*;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamDAOImpl implements ExamDAO {
	@Override
	public boolean exists(PersistentSession session, int examID) throws PersistentException {
		return this.getExamByORMID(session, examID) != null;
	}

	public boolean exists(PersistentSession session, int groupID, String examName) throws PersistentException {
		ExamCriteria criteria = new ExamCriteria(session);
		criteria._groupId.eq(groupID);
		criteria.name.eq(examName);
		return loadExamByCriteria(criteria) != null;
	}

	private static final org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(ExamDAOImpl.class);
	public Exam loadExamByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadExamByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("loadExamByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Exam getExamByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getExamByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("getExamByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Exam loadExamByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadExamByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadExamByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Exam getExamByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getExamByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("getExamByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Exam loadExamByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Exam) session.load(Exam.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("loadExamByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Exam getExamByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Exam) session.get(Exam.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("getExamByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Exam loadExamByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Exam) session.load(Exam.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("loadExamByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Exam getExamByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Exam) session.get(Exam.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("getExamByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryExam(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryExam(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("queryExam(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryExam(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryExam(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("queryExam(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Exam[] listExamByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listExamByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("listExamByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Exam[] listExamByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listExamByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("listExamByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryExam(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Exam as Exam");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listExamByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryExam(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Exam as Exam");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Exam", lockMode);
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listExamByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Exam[] listExamByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryExam(session, condition, orderBy);
			return (Exam[]) list.toArray(new Exam[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listExamByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Exam[] listExamByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryExam(session, condition, orderBy, lockMode);
			return (Exam[]) list.toArray(new Exam[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listExamByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Exam loadExamByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadExamByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("loadExamByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Exam loadExamByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadExamByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadExamByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Exam loadExamByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Exam[] exams = listExamByQuery(session, condition, orderBy);
		if (exams != null && exams.length > 0)
			return exams[0];
		else
			return null;
	}
	
	public Exam loadExamByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Exam[] exams = listExamByQuery(session, condition, orderBy, lockMode);
		if (exams != null && exams.length > 0)
			return exams[0];
		else
			return null;
	}
	
	public java.util.Iterator iterateExamByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateExamByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("iterateExamByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateExamByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateExamByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("iterateExamByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateExamByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Exam as Exam");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateExamByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateExamByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Exam as Exam");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Exam", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateExamByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Exam createExam() {
		return new Exam();
	}
	
	public boolean save(Exam exam) throws PersistentException {
		try {
			ClassesPersistentManager.instance().saveObject(exam);
			return true;
		}
		catch (Exception e) {
			_logger.error("save(Exam exam)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean delete(Exam exam) throws PersistentException {
		try {
			ClassesPersistentManager.instance().deleteObject(exam);
			return true;
		}
		catch (Exception e) {
			_logger.error("delete(Exam exam)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(Exam exam)throws PersistentException {
		try {
			if (exam.get_group() != null) {
				exam.get_group()._exams.remove(exam);
			}
			
			Submission[] l_submissionss = exam._submissions.toArray();
			for(int i = 0; i < l_submissionss.length; i++) {
				l_submissionss[i].set_exam(null);
			}
			return delete(exam);
		}
		catch(Exception e) {
			_logger.error("deleteAndDissociate()", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(Exam exam, org.orm.PersistentSession session)throws PersistentException {
		try {
			if (exam.get_group() != null) {
				exam.get_group()._exams.remove(exam);
			}
			
			Submission[] l_submissionss = exam._submissions.toArray();
			for(int i = 0; i < l_submissionss.length; i++) {
				l_submissionss[i].set_exam(null);
			}
			try {
				session.delete(exam);
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
	
	public boolean refresh(Exam exam) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().refresh(exam);
			return true;
		}
		catch (Exception e) {
			_logger.error("refresh(Exam exam)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean evict(Exam exam) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().evict(exam);
			return true;
		}
		catch (Exception e) {
			_logger.error("evict(Exam exam)", e);
			throw new PersistentException(e);
		}
	}
	
	public Exam loadExamByCriteria(ExamCriteria examCriteria) {
		Exam[] exams = listExamByCriteria(examCriteria);
		if(exams == null || exams.length == 0) {
			return null;
		}
		return exams[0];
	}
	
	public Exam[] listExamByCriteria(ExamCriteria examCriteria) {
		return examCriteria.listExam();
	}
}
