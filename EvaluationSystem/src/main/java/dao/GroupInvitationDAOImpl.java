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

public class GroupInvitationDAOImpl implements GroupInvitationDAO {
	private static final org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(GroupInvitationDAOImpl.class);
	public GroupInvitation loadGroupInvitationByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadGroupInvitationByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("loadGroupInvitationByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupInvitation getGroupInvitationByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getGroupInvitationByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("getGroupInvitationByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupInvitation loadGroupInvitationByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadGroupInvitationByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadGroupInvitationByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupInvitation getGroupInvitationByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getGroupInvitationByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("getGroupInvitationByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupInvitation loadGroupInvitationByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (GroupInvitation) session.load(GroupInvitation.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("loadGroupInvitationByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupInvitation getGroupInvitationByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (GroupInvitation) session.get(GroupInvitation.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("getGroupInvitationByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupInvitation loadGroupInvitationByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (GroupInvitation) session.load(GroupInvitation.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("loadGroupInvitationByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupInvitation getGroupInvitationByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (GroupInvitation) session.get(GroupInvitation.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("getGroupInvitationByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryGroupInvitation(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryGroupInvitation(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("queryGroupInvitation(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryGroupInvitation(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryGroupInvitation(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("queryGroupInvitation(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupInvitation[] listGroupInvitationByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listGroupInvitationByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("listGroupInvitationByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupInvitation[] listGroupInvitationByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listGroupInvitationByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("listGroupInvitationByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryGroupInvitation(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.GroupInvitation as model.GroupInvitation");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listGroupInvitationByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryGroupInvitation(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.GroupInvitation as model.GroupInvitation");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.GroupInvitation", lockMode);
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listGroupInvitationByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupInvitation[] listGroupInvitationByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryGroupInvitation(session, condition, orderBy);
			return (GroupInvitation[]) list.toArray(new GroupInvitation[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listGroupInvitationByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupInvitation[] listGroupInvitationByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			List list = queryGroupInvitation(session, condition, orderBy, lockMode);
			return (GroupInvitation[]) list.toArray(new GroupInvitation[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listGroupInvitationByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupInvitation loadGroupInvitationByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadGroupInvitationByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("loadGroupInvitationByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupInvitation loadGroupInvitationByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadGroupInvitationByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadGroupInvitationByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupInvitation loadGroupInvitationByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		GroupInvitation[] groupInvitations = listGroupInvitationByQuery(session, condition, orderBy);
		if (groupInvitations != null && groupInvitations.length > 0)
			return groupInvitations[0];
		else
			return null;
	}
	
	public GroupInvitation loadGroupInvitationByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		GroupInvitation[] groupInvitations = listGroupInvitationByQuery(session, condition, orderBy, lockMode);
		if (groupInvitations != null && groupInvitations.length > 0)
			return groupInvitations[0];
		else
			return null;
	}
	
	public java.util.Iterator iterateGroupInvitationByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateGroupInvitationByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("iterateGroupInvitationByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateGroupInvitationByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateGroupInvitationByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("iterateGroupInvitationByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateGroupInvitationByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.GroupInvitation as model.GroupInvitation");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateGroupInvitationByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateGroupInvitationByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.GroupInvitation as model.GroupInvitation");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.GroupInvitation", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateGroupInvitationByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupInvitation createGroupInvitation() {
		return new GroupInvitation();
	}
	
	public boolean save(GroupInvitation groupInvitation) throws PersistentException {
		try {
			ClassesPersistentManager.instance().saveObject(groupInvitation);
			return true;
		}
		catch (Exception e) {
			_logger.error("save(model.GroupInvitation groupInvitation)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean delete(GroupInvitation groupInvitation) throws PersistentException {
		try {
			ClassesPersistentManager.instance().deleteObject(groupInvitation);
			return true;
		}
		catch (Exception e) {
			_logger.error("delete(model.GroupInvitation groupInvitation)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(GroupInvitation groupInvitation)throws PersistentException {
		try {
			if (groupInvitation.get_user() != null) {
				groupInvitation.get_user()._notifications.remove(groupInvitation);
			}
			
			return delete(groupInvitation);
		}
		catch(Exception e) {
			_logger.error("deleteAndDissociate()", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(GroupInvitation groupInvitation, PersistentSession session)throws PersistentException {
		try {
			if (groupInvitation.get_user() != null) {
				groupInvitation.get_user()._notifications.remove(groupInvitation);
			}
			
			try {
				session.delete(groupInvitation);
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
	
	public boolean refresh(GroupInvitation groupInvitation) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().refresh(groupInvitation);
			return true;
		}
		catch (Exception e) {
			_logger.error("refresh(model.GroupInvitation groupInvitation)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean evict(GroupInvitation groupInvitation) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().evict(groupInvitation);
			return true;
		}
		catch (Exception e) {
			_logger.error("evict(model.GroupInvitation groupInvitation)", e);
			throw new PersistentException(e);
		}
	}
	
	public GroupInvitation loadGroupInvitationByCriteria(GroupInvitationCriteria groupInvitationCriteria) {
		GroupInvitation[] groupInvitations = listGroupInvitationByCriteria(groupInvitationCriteria);
		if(groupInvitations == null || groupInvitations.length == 0) {
			return null;
		}
		return groupInvitations[0];
	}
	
	public GroupInvitation[] listGroupInvitationByCriteria(GroupInvitationCriteria groupInvitationCriteria) {
		return groupInvitationCriteria.listGroupInvitation();
	}
}
