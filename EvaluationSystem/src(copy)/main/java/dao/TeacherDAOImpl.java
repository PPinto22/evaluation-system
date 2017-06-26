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
import model.persistent.Class;
import model.persistent.Notification;
import model.persistent.Teacher;
import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherDAOImpl implements TeacherDAO {
	@Override
	public Teacher loadTeacherByEmail(String email) throws PersistentException {
		TeacherCriteria criteria = new TeacherCriteria();
		criteria.email.eq(email);
		criteria.deleted.eq(false);
		return this.loadTeacherByCriteria(criteria);
	}

	@Override
	public boolean exists(int ID) throws PersistentException {
		TeacherCriteria criteria = new TeacherCriteria();
		criteria.ID.eq(ID);
		criteria.deleted.eq(false);
		return this.loadTeacherByCriteria(criteria) != null;
	}

	@Override
	public boolean exists(String email) throws PersistentException {
		TeacherCriteria criteria = new TeacherCriteria();
		criteria.email.eq(email);
		criteria.deleted.eq(false);
		return this.loadTeacherByCriteria(criteria) != null;
	}

	@Override
	public boolean existsActive(String email) throws PersistentException {
		TeacherCriteria criteria = new TeacherCriteria();
		criteria.email.eq(email);
		criteria.registered.eq(true);
		criteria.deleted.eq(false);
		return this.loadTeacherByCriteria(criteria) != null;
	}

	private static final org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(TeacherDAOImpl.class);
	public Teacher loadTeacherByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadTeacherByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("loadTeacherByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Teacher getTeacherByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getTeacherByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("getTeacherByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Teacher loadTeacherByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadTeacherByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadTeacherByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Teacher getTeacherByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getTeacherByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("getTeacherByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Teacher loadTeacherByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Teacher) session.load(Teacher.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("loadTeacherByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Teacher getTeacherByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Teacher) session.get(Teacher.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("getTeacherByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Teacher loadTeacherByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (Teacher) session.load(Teacher.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("loadTeacherByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Teacher getTeacherByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (Teacher) session.get(Teacher.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("getTeacherByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryTeacher(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryTeacher(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("queryTeacher(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryTeacher(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryTeacher(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("queryTeacher(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Teacher[] listTeacherByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listTeacherByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("listTeacherByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Teacher[] listTeacherByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listTeacherByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("listTeacherByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryTeacher(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.persistent.Teacher as model.persistent.Teacher");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listTeacherByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryTeacher(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.persistent.Teacher as model.persistent.Teacher");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.persistent.Teacher", lockMode);
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listTeacherByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Teacher[] listTeacherByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryTeacher(session, condition, orderBy);
			return (Teacher[]) list.toArray(new Teacher[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listTeacherByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Teacher[] listTeacherByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			List list = queryTeacher(session, condition, orderBy, lockMode);
			return (Teacher[]) list.toArray(new Teacher[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listTeacherByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Teacher loadTeacherByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadTeacherByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("loadTeacherByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Teacher loadTeacherByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadTeacherByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadTeacherByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Teacher loadTeacherByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Teacher[] teachers = listTeacherByQuery(session, condition, orderBy);
		if (teachers != null && teachers.length > 0)
			return teachers[0];
		else
			return null;
	}
	
	public Teacher loadTeacherByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		Teacher[] teachers = listTeacherByQuery(session, condition, orderBy, lockMode);
		if (teachers != null && teachers.length > 0)
			return teachers[0];
		else
			return null;
	}
	
	public java.util.Iterator iterateTeacherByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateTeacherByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("iterateTeacherByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateTeacherByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateTeacherByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("iterateTeacherByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateTeacherByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.persistent.Teacher as model.persistent.Teacher");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateTeacherByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateTeacherByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.persistent.Teacher as model.persistent.Teacher");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.persistent.Teacher", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateTeacherByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Teacher createTeacher() {
		return new Teacher();
	}
	
	public boolean save(Teacher teacher) throws PersistentException {
		try {
			ClassesPersistentManager.instance().saveObject(teacher);
			return true;
		}
		catch (Exception e) {
			_logger.error("save(model.persistent.Teacher teacher)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean delete(Teacher teacher) throws PersistentException {
		try {
			ClassesPersistentManager.instance().deleteObject(teacher);
			return true;
		}
		catch (Exception e) {
			_logger.error("delete(model.persistent.Teacher teacher)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(Teacher teacher)throws PersistentException {
		try {
			Class[] l_classess = teacher._classes.toArray();
			for(int i = 0; i < l_classess.length; i++) {
				l_classess[i].set_teacher(null);
			}
			Notification[] l_notificationss = teacher._notifications.toArray();
			for(int i = 0; i < l_notificationss.length; i++) {
				l_notificationss[i].set_user(null);
			}
			return delete(teacher);
		}
		catch(Exception e) {
			_logger.error("deleteAndDissociate()", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(Teacher teacher, PersistentSession session)throws PersistentException {
		try {
			Class[] l_classess = teacher._classes.toArray();
			for(int i = 0; i < l_classess.length; i++) {
				l_classess[i].set_teacher(null);
			}
			Notification[] l_notificationss = teacher._notifications.toArray();
			for(int i = 0; i < l_notificationss.length; i++) {
				l_notificationss[i].set_user(null);
			}
			try {
				session.delete(teacher);
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
	
	public boolean refresh(Teacher teacher) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().refresh(teacher);
			return true;
		}
		catch (Exception e) {
			_logger.error("refresh(model.persistent.Teacher teacher)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean evict(Teacher teacher) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().evict(teacher);
			return true;
		}
		catch (Exception e) {
			_logger.error("evict(model.persistent.Teacher teacher)", e);
			throw new PersistentException(e);
		}
	}
	
	public Teacher loadTeacherByCriteria(TeacherCriteria teacherCriteria) {
		Teacher[] teachers = listTeacherByCriteria(teacherCriteria);
		if(teachers == null || teachers.length == 0) {
			return null;
		}
		return teachers[0];
	}
	
	public Teacher[] listTeacherByCriteria(TeacherCriteria teacherCriteria) {
		return teacherCriteria.listTeacher();
	}
}
