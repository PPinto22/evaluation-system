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
import exception.NonExistentEntityException;
import model.Class;
import model.Group;
import model.Question;
import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassDAOImpl implements ClassDAO {

	// Nosso codigo
	@Override
	public boolean exists(int ID) throws PersistentException {
		ClassCriteria criteria = new ClassCriteria();
		criteria.ID.eq(ID);
		return this.loadClassByCriteria(criteria) != null;
	}

	@Override
	public boolean exists(int teacherID, String className) throws PersistentException {
		ClassCriteria criteria = new ClassCriteria();
		criteria._teacherId.eq(teacherID);
		criteria.name.eq(className);
		return this.loadClassByCriteria(criteria) != null;
	}

	@Override
	public Class getClassByName(int teacherID, String className) throws PersistentException, NonExistentEntityException {
		ClassCriteria criteria = new ClassCriteria();
		criteria._teacherId.eq(teacherID);
		criteria.name.eq(className);
		Class cl = this.loadClassByCriteria(criteria);
		if(cl == null)
			throw new NonExistentEntityException();
		else
			return cl;
	}

	// Codigo gerado
	private static final org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(ClassDAOImpl.class);
	public Class loadClassByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadClassByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("loadClassByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Class getClassByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getClassByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("getClassByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Class loadClassByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadClassByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadClassByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Class getClassByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getClassByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("getClassByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Class loadClassByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Class) session.load(Class.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("loadClassByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Class getClassByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Class) session.get(Class.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("getClassByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Class loadClassByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (Class) session.load(Class.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("loadClassByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Class getClassByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (Class) session.get(Class.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("getClassByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryClass(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryClass(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("queryClass(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryClass(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryClass(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("queryClass(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Class[] listClassByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listClassByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("listClassByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Class[] listClassByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listClassByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("listClassByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryClass(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.Class as model.Class");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listClassByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryClass(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.Class as model.Class");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.Class", lockMode);
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listClassByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Class[] listClassByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryClass(session, condition, orderBy);
			return (Class[]) list.toArray(new Class[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listClassByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Class[] listClassByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			List list = queryClass(session, condition, orderBy, lockMode);
			return (Class[]) list.toArray(new Class[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listClassByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Class loadClassByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadClassByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("loadClassByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Class loadClassByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadClassByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadClassByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Class loadClassByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Class[] classes = listClassByQuery(session, condition, orderBy);
		if (classes != null && classes.length > 0)
			return classes[0];
		else
			return null;
	}
	
	public Class loadClassByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		Class[] classes = listClassByQuery(session, condition, orderBy, lockMode);
		if (classes != null && classes.length > 0)
			return classes[0];
		else
			return null;
	}
	
	public java.util.Iterator iterateClassByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateClassByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("iterateClassByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateClassByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateClassByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("iterateClassByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateClassByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.Class as model.Class");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateClassByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateClassByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.Class as model.Class");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.Class", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateClassByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Class createClass() {
		return new Class();
	}
	
	public boolean save(Class class1) throws PersistentException {
		try {
			ClassesPersistentManager.instance().saveObject(class1);
			return true;
		}
		catch (Exception e) {
			_logger.error("save(model.Class class1)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean delete(Class class1) throws PersistentException {
		try {
			ClassesPersistentManager.instance().deleteObject(class1);
			return true;
		}
		catch (Exception e) {
			_logger.error("delete(model.Class class1)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(Class class1)throws PersistentException {
		try {
			if (class1.get_teacher() != null) {
				class1.get_teacher()._classes.remove(class1);
			}
			
			Question[] l_questions = class1._question.toArray();
			for(int i = 0; i < l_questions.length; i++) {
				l_questions[i].set_class(null);
			}
			Group[] l_groupss = class1._groups.toArray();
			for(int i = 0; i < l_groupss.length; i++) {
				l_groupss[i].set_class(null);
			}
			return delete(class1);
		}
		catch(Exception e) {
			_logger.error("deleteAndDissociate()", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(Class class1, PersistentSession session)throws PersistentException {
		try {
			if (class1.get_teacher() != null) {
				class1.get_teacher()._classes.remove(class1);
			}
			
			Question[] l_questions = class1._question.toArray();
			for(int i = 0; i < l_questions.length; i++) {
				l_questions[i].set_class(null);
			}
			Group[] l_groupss = class1._groups.toArray();
			for(int i = 0; i < l_groupss.length; i++) {
				l_groupss[i].set_class(null);
			}
			try {
				session.delete(class1);
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
	
	public boolean refresh(Class class1) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().refresh(class1);
			return true;
		}
		catch (Exception e) {
			_logger.error("refresh(model.Class class1)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean evict(Class class1) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().evict(class1);
			return true;
		}
		catch (Exception e) {
			_logger.error("evict(model.Class class1)", e);
			throw new PersistentException(e);
		}
	}
	
	public Class loadClassByCriteria(ClassCriteria classCriteria) {
		Class[] classes = listClassByCriteria(classCriteria);
		if(classes == null || classes.length == 0) {
			return null;
		}
		return classes[0];
	}
	
	public Class[] listClassByCriteria(ClassCriteria classCriteria) {
		return classCriteria.listClass();
	}
}
