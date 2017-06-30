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
import model.GroupStudent;
import model.Notification;
import model.Student;
import model.Submission;
import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Override
	public Student loadStudentByEmail(PersistentSession session, String email) throws PersistentException {
		StudentCriteria criteria = new StudentCriteria(session);
		criteria.email.eq(email);
		criteria.deleted.eq(false);
		return this.loadStudentByCriteria(criteria);
	}

	@Override
	public boolean exists(PersistentSession session, int ID) throws PersistentException {
		StudentCriteria criteria = new StudentCriteria(session);
		criteria.ID.eq(ID);
		criteria.deleted.eq(false);
		return this.loadStudentByCriteria(criteria) != null;
	}

	@Override
	public boolean exists(PersistentSession session, String email) throws PersistentException {
		StudentCriteria criteria = new StudentCriteria(session);
		criteria.email.eq(email);
		criteria.deleted.eq(false);
		return this.loadStudentByCriteria(criteria) != null;
	}

	@Override
	public boolean existsActive(PersistentSession session, String email) throws PersistentException {
		StudentCriteria criteria = new StudentCriteria(session);
		criteria.email.eq(email);
		criteria.registered.eq(true);
		criteria.deleted.eq(false);
		return this.loadStudentByCriteria(criteria) != null;
	}

	private static final org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(StudentDAOImpl.class);
	public Student loadStudentByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadStudentByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("loadStudentByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Student getStudentByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getStudentByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("getStudentByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Student loadStudentByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadStudentByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadStudentByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Student getStudentByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getStudentByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("getStudentByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Student loadStudentByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Student) session.load(Student.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("loadStudentByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Student getStudentByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Student) session.get(Student.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("getStudentByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Student loadStudentByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (Student) session.load(Student.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("loadStudentByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Student getStudentByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (Student) session.get(Student.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("getStudentByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryStudent(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryStudent(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("queryStudent(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryStudent(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryStudent(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("queryStudent(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Student[] listStudentByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listStudentByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("listStudentByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Student[] listStudentByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listStudentByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("listStudentByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryStudent(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.Student as model.Student");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listStudentByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryStudent(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.Student as model.Student");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.Student", lockMode);
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listStudentByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Student[] listStudentByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryStudent(session, condition, orderBy);
			return (Student[]) list.toArray(new Student[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listStudentByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Student[] listStudentByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			List list = queryStudent(session, condition, orderBy, lockMode);
			return (Student[]) list.toArray(new Student[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listStudentByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Student loadStudentByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadStudentByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("loadStudentByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Student loadStudentByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadStudentByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadStudentByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Student loadStudentByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Student[] students = listStudentByQuery(session, condition, orderBy);
		if (students != null && students.length > 0)
			return students[0];
		else
			return null;
	}
	
	public Student loadStudentByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		Student[] students = listStudentByQuery(session, condition, orderBy, lockMode);
		if (students != null && students.length > 0)
			return students[0];
		else
			return null;
	}
	
	public java.util.Iterator iterateStudentByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateStudentByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("iterateStudentByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateStudentByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateStudentByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("iterateStudentByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateStudentByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.Student as model.Student");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateStudentByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateStudentByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.Student as model.Student");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.Student", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateStudentByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Student createStudent() {
		return new Student();
	}
	
	public boolean save(Student student) throws PersistentException {
		try {
			ClassesPersistentManager.instance().saveObject(student);
			return true;
		}
		catch (Exception e) {
			_logger.error("save(model.Student student)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean delete(Student student) throws PersistentException {
		try {
			ClassesPersistentManager.instance().deleteObject(student);
			return true;
		}
		catch (Exception e) {
			_logger.error("delete(model.Student student)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(Student student)throws PersistentException {
		try {
			Submission[] l_submissionss = student._submissions.toArray();
			for(int i = 0; i < l_submissionss.length; i++) {
				l_submissionss[i].set_student(null);
			}
			GroupStudent[] l_groupss = student._groups.toArray();
			for(int i = 0; i < l_groupss.length; i++) {
				l_groupss[i].set_student(null);
			}
			Notification[] l_notificationss = student._notifications.toArray();
			for(int i = 0; i < l_notificationss.length; i++) {
				l_notificationss[i].set_user(null);
			}
			return delete(student);
		}
		catch(Exception e) {
			_logger.error("deleteAndDissociate()", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(Student student, PersistentSession session)throws PersistentException {
		try {
			Submission[] l_submissionss = student._submissions.toArray();
			for(int i = 0; i < l_submissionss.length; i++) {
				l_submissionss[i].set_student(null);
			}
			GroupStudent[] l_groupss = student._groups.toArray();
			for(int i = 0; i < l_groupss.length; i++) {
				l_groupss[i].set_student(null);
			}
			Notification[] l_notificationss = student._notifications.toArray();
			for(int i = 0; i < l_notificationss.length; i++) {
				l_notificationss[i].set_user(null);
			}
			try {
				session.delete(student);
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
	
	public boolean refresh(Student student) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().refresh(student);
			return true;
		}
		catch (Exception e) {
			_logger.error("refresh(model.Student student)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean evict(Student student) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().evict(student);
			return true;
		}
		catch (Exception e) {
			_logger.error("evict(model.Student student)", e);
			throw new PersistentException(e);
		}
	}
	
	public Student loadStudentByCriteria(StudentCriteria studentCriteria) {
		Student[] students = listStudentByCriteria(studentCriteria);
		if(students == null || students.length == 0) {
			return null;
		}
		return students[0];
	}
	
	public Student[] listStudentByCriteria(StudentCriteria studentCriteria) {
		return studentCriteria.listStudent();
	}
}
