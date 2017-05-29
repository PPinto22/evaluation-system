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
import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupStudentDAOImpl implements GroupStudentDAO {
	private static final org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(GroupStudentDAOImpl.class);
	public GroupStudent loadGroupStudentByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadGroupStudentByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("loadGroupStudentByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupStudent getGroupStudentByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getGroupStudentByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("getGroupStudentByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupStudent loadGroupStudentByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadGroupStudentByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadGroupStudentByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupStudent getGroupStudentByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getGroupStudentByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("getGroupStudentByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupStudent loadGroupStudentByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (GroupStudent) session.load(GroupStudent.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("loadGroupStudentByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupStudent getGroupStudentByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (GroupStudent) session.get(GroupStudent.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("getGroupStudentByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupStudent loadGroupStudentByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (GroupStudent) session.load(GroupStudent.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("loadGroupStudentByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupStudent getGroupStudentByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (GroupStudent) session.get(GroupStudent.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("getGroupStudentByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryGroupStudent(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryGroupStudent(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("queryGroupStudent(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryGroupStudent(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryGroupStudent(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("queryGroupStudent(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupStudent[] listGroupStudentByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listGroupStudentByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("listGroupStudentByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupStudent[] listGroupStudentByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listGroupStudentByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("listGroupStudentByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryGroupStudent(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.GroupStudent as model.GroupStudent");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listGroupStudentByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryGroupStudent(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.GroupStudent as model.GroupStudent");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.GroupStudent", lockMode);
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listGroupStudentByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupStudent[] listGroupStudentByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryGroupStudent(session, condition, orderBy);
			return (GroupStudent[]) list.toArray(new GroupStudent[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listGroupStudentByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupStudent[] listGroupStudentByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			List list = queryGroupStudent(session, condition, orderBy, lockMode);
			return (GroupStudent[]) list.toArray(new GroupStudent[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listGroupStudentByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupStudent loadGroupStudentByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadGroupStudentByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("loadGroupStudentByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupStudent loadGroupStudentByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadGroupStudentByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadGroupStudentByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupStudent loadGroupStudentByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		GroupStudent[] groupStudents = listGroupStudentByQuery(session, condition, orderBy);
		if (groupStudents != null && groupStudents.length > 0)
			return groupStudents[0];
		else
			return null;
	}
	
	public GroupStudent loadGroupStudentByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		GroupStudent[] groupStudents = listGroupStudentByQuery(session, condition, orderBy, lockMode);
		if (groupStudents != null && groupStudents.length > 0)
			return groupStudents[0];
		else
			return null;
	}
	
	public java.util.Iterator iterateGroupStudentByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateGroupStudentByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("iterateGroupStudentByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateGroupStudentByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateGroupStudentByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("iterateGroupStudentByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateGroupStudentByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.GroupStudent as model.GroupStudent");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateGroupStudentByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateGroupStudentByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.GroupStudent as model.GroupStudent");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.GroupStudent", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateGroupStudentByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupStudent createGroupStudent() {
		return new GroupStudent();
	}
	
	public boolean save(GroupStudent groupStudent) throws PersistentException {
		try {
			ClassesPersistentManager.instance().saveObject(groupStudent);
			return true;
		}
		catch (Exception e) {
			_logger.error("save(model.GroupStudent groupStudent)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean delete(GroupStudent groupStudent) throws PersistentException {
		try {
			ClassesPersistentManager.instance().deleteObject(groupStudent);
			return true;
		}
		catch (Exception e) {
			_logger.error("delete(model.GroupStudent groupStudent)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(GroupStudent groupStudent)throws PersistentException {
		try {
			if (groupStudent.get_student() != null) {
				groupStudent.get_student()._groups.remove(groupStudent);
			}
			
			if (groupStudent.get_group() != null) {
				groupStudent.get_group()._students.remove(groupStudent);
			}
			
			return delete(groupStudent);
		}
		catch(Exception e) {
			_logger.error("deleteAndDissociate()", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(GroupStudent groupStudent, PersistentSession session)throws PersistentException {
		try {
			if (groupStudent.get_student() != null) {
				groupStudent.get_student()._groups.remove(groupStudent);
			}
			
			if (groupStudent.get_group() != null) {
				groupStudent.get_group()._students.remove(groupStudent);
			}
			
			try {
				session.delete(groupStudent);
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
	
	public boolean refresh(GroupStudent groupStudent) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().refresh(groupStudent);
			return true;
		}
		catch (Exception e) {
			_logger.error("refresh(model.GroupStudent groupStudent)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean evict(GroupStudent groupStudent) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().evict(groupStudent);
			return true;
		}
		catch (Exception e) {
			_logger.error("evict(model.GroupStudent groupStudent)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupStudent loadGroupStudentByCriteria(GroupStudentCriteria groupStudentCriteria) {
		GroupStudent[] groupStudents = listGroupStudentByCriteria(groupStudentCriteria);
		if(groupStudents == null || groupStudents.length == 0) {
			return null;
		}
		return groupStudents[0];
	}
	
	public GroupStudent[] listGroupStudentByCriteria(GroupStudentCriteria groupStudentCriteria) {
		return groupStudentCriteria.listGroupStudent();
	}
}
