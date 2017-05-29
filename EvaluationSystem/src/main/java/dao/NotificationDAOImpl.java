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
import model.GroupInvitation;
import model.Notification;
import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationDAOImpl implements NotificationDAO {
	private static final org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(NotificationDAOImpl.class);
	public Notification loadNotificationByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadNotificationByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("loadNotificationByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Notification getNotificationByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getNotificationByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("getNotificationByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Notification loadNotificationByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadNotificationByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadNotificationByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Notification getNotificationByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getNotificationByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("getNotificationByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Notification loadNotificationByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Notification) session.load(Notification.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("loadNotificationByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Notification getNotificationByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Notification) session.get(Notification.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("getNotificationByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public Notification loadNotificationByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (Notification) session.load(Notification.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("loadNotificationByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public Notification getNotificationByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (Notification) session.get(Notification.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("getNotificationByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryNotification(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryNotification(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("queryNotification(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryNotification(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryNotification(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("queryNotification(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Notification[] listNotificationByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listNotificationByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("listNotificationByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Notification[] listNotificationByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listNotificationByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("listNotificationByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryNotification(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.Notification as model.Notification");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listNotificationByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryNotification(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.Notification as model.Notification");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.Notification", lockMode);
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listNotificationByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Notification[] listNotificationByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryNotification(session, condition, orderBy);
			return (Notification[]) list.toArray(new Notification[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listNotificationByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Notification[] listNotificationByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			List list = queryNotification(session, condition, orderBy, lockMode);
			return (Notification[]) list.toArray(new Notification[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listNotificationByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Notification loadNotificationByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadNotificationByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("loadNotificationByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Notification loadNotificationByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadNotificationByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadNotificationByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Notification loadNotificationByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Notification[] notifications = listNotificationByQuery(session, condition, orderBy);
		if (notifications != null && notifications.length > 0)
			return notifications[0];
		else
			return null;
	}
	
	public Notification loadNotificationByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		Notification[] notifications = listNotificationByQuery(session, condition, orderBy, lockMode);
		if (notifications != null && notifications.length > 0)
			return notifications[0];
		else
			return null;
	}
	
	public java.util.Iterator iterateNotificationByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateNotificationByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("iterateNotificationByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateNotificationByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateNotificationByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("iterateNotificationByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateNotificationByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.Notification as model.Notification");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateNotificationByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateNotificationByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.Notification as model.Notification");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.Notification", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateNotificationByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public Notification createNotification() {
		return new Notification();
	}
	
	public boolean save(Notification notification) throws PersistentException {
		try {
			ClassesPersistentManager.instance().saveObject(notification);
			return true;
		}
		catch (Exception e) {
			_logger.error("save(model.Notification notification)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean delete(Notification notification) throws PersistentException {
		try {
			ClassesPersistentManager.instance().deleteObject(notification);
			return true;
		}
		catch (Exception e) {
			_logger.error("delete(model.Notification notification)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(Notification notification)throws PersistentException {
		if (notification instanceof GroupInvitation) {
			return DAOFactory.getDAOFactory().getGroupInvitationDAO().deleteAndDissociate((GroupInvitation) notification);
		}
		
		try {
			if (notification.get_user() != null) {
				notification.get_user()._notifications.remove(notification);
			}
			
			return delete(notification);
		}
		catch(Exception e) {
			_logger.error("deleteAndDissociate()", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(Notification notification, PersistentSession session)throws PersistentException {
		if (notification instanceof GroupInvitation) {
			return DAOFactory.getDAOFactory().getGroupInvitationDAO().deleteAndDissociate((GroupInvitation) notification, session);
		}
		
		try {
			if (notification.get_user() != null) {
				notification.get_user()._notifications.remove(notification);
			}
			
			try {
				session.delete(notification);
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
	
	public boolean refresh(Notification notification) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().refresh(notification);
			return true;
		}
		catch (Exception e) {
			_logger.error("refresh(model.Notification notification)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean evict(Notification notification) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().evict(notification);
			return true;
		}
		catch (Exception e) {
			_logger.error("evict(model.Notification notification)", e);
			throw new PersistentException(e);
		}
	}
	
	public Notification loadNotificationByCriteria(NotificationCriteria notificationCriteria) {
		Notification[] notifications = listNotificationByCriteria(notificationCriteria);
		if(notifications == null || notifications.length == 0) {
			return null;
		}
		return notifications[0];
	}
	
	public Notification[] listNotificationByCriteria(NotificationCriteria notificationCriteria) {
		return notificationCriteria.listNotification();
	}
}
