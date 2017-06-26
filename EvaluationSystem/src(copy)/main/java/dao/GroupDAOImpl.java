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
import model.persistent.Exam;
import model.persistent.Group;
import model.persistent.GroupStudent;
import model.persistent.Class;
import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDAOImpl implements GroupDAO {
	@Override
	public boolean exists(int ID) throws PersistentException {
		GroupCriteria criteria = new GroupCriteria();
		criteria.ID.eq(ID);
		Group group = this.loadGroupByCriteria(criteria);
		return group != null;
	}

	@Override
	public boolean exists(Class cl, String name) throws PersistentException {
		return this.loadGroupByName(cl,name) != null;
	}

	@Override
	public Group loadGroupByName(Class cl, String name) throws PersistentException {
		GroupCriteria criteria = new GroupCriteria();
		criteria.name.eq(name);
		criteria._classId.eq(cl.getID());
		Group group = this.loadGroupByCriteria(criteria);
		return group;
	}

	private static final org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(GroupDAOImpl.class);
	public Group loadGroupByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadGroupByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("loadGroupByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Group getGroupByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getGroupByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("getGroupByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Group loadGroupByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadGroupByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadGroupByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Group getGroupByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getGroupByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("getGroupByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Group loadGroupByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Group) session.load(Group.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("loadGroupByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Group getGroupByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Group) session.get(Group.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("getGroupByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Group loadGroupByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (Group) session.load(Group.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("loadGroupByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Group getGroupByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (Group) session.get(Group.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("getGroupByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryGroup(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryGroup(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("queryGroup(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryGroup(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryGroup(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("queryGroup(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Group[] listGroupByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listGroupByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("listGroupByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Group[] listGroupByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listGroupByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("listGroupByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryGroup(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.persistent.Group as model.persistent.Group");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listGroupByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryGroup(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.persistent.Group as model.persistent.Group");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.persistent.Group", lockMode);
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listGroupByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Group[] listGroupByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryGroup(session, condition, orderBy);
			return (Group[]) list.toArray(new Group[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listGroupByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Group[] listGroupByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			List list = queryGroup(session, condition, orderBy, lockMode);
			return (Group[]) list.toArray(new Group[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listGroupByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Group loadGroupByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadGroupByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("loadGroupByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Group loadGroupByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadGroupByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadGroupByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Group loadGroupByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Group[] groups = listGroupByQuery(session, condition, orderBy);
		if (groups != null && groups.length > 0)
			return groups[0];
		else
			return null;
	}
	
	public Group loadGroupByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		Group[] groups = listGroupByQuery(session, condition, orderBy, lockMode);
		if (groups != null && groups.length > 0)
			return groups[0];
		else
			return null;
	}
	
	public java.util.Iterator iterateGroupByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateGroupByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("iterateGroupByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateGroupByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateGroupByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("iterateGroupByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateGroupByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.persistent.Group as model.persistent.Group");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateGroupByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateGroupByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.persistent.Group as model.persistent.Group");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.persistent.Group", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateGroupByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Group createGroup() {
		return new Group();
	}
	
	public boolean save(Group group) throws PersistentException {
		try {
			ClassesPersistentManager.instance().saveObject(group);
			return true;
		}
		catch (Exception e) {
			_logger.error("save(model.persistent.Group group)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean delete(Group group) throws PersistentException {
		try {
			ClassesPersistentManager.instance().deleteObject(group);
			return true;
		}
		catch (Exception e) {
			_logger.error("delete(model.persistent.Group group)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(Group group)throws PersistentException {
		try {
			if (group.get_class() != null) {
				group.get_class()._groups.remove(group);
			}
			
			GroupStudent[] l_studentss = group._students.toArray();
			for(int i = 0; i < l_studentss.length; i++) {
				l_studentss[i].set_group(null);
			}
			Exam[] l_examss = group._exams.toArray();
			for(int i = 0; i < l_examss.length; i++) {
				l_examss[i].set_group(null);
			}
			return delete(group);
		}
		catch(Exception e) {
			_logger.error("deleteAndDissociate()", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(Group group, PersistentSession session)throws PersistentException {
		try {
			if (group.get_class() != null) {
				group.get_class()._groups.remove(group);
			}
			
			GroupStudent[] l_studentss = group._students.toArray();
			for(int i = 0; i < l_studentss.length; i++) {
				l_studentss[i].set_group(null);
			}
			Exam[] l_examss = group._exams.toArray();
			for(int i = 0; i < l_examss.length; i++) {
				l_examss[i].set_group(null);
			}
			try {
				session.delete(group);
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
	
	public boolean refresh(Group group) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().refresh(group);
			return true;
		}
		catch (Exception e) {
			_logger.error("refresh(model.persistent.Group group)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean evict(Group group) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().evict(group);
			return true;
		}
		catch (Exception e) {
			_logger.error("evict(model.persistent.Group group)", e);
			throw new PersistentException(e);
		}
	}
	
	public Group loadGroupByCriteria(GroupCriteria groupCriteria) {
		Group[] groups = listGroupByCriteria(groupCriteria);
		if(groups == null || groups.length == 0) {
			return null;
		}
		return groups[0];
	}
	
	public Group[] listGroupByCriteria(GroupCriteria groupCriteria) {
		return groupCriteria.listGroup();
	}
}
