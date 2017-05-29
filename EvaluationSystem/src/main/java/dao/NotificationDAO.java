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
import model.Notification;
import org.orm.*;
import org.hibernate.LockMode;

public interface NotificationDAO {
	public Notification loadNotificationByORMID(int ID) throws PersistentException;
	public Notification getNotificationByORMID(int ID) throws PersistentException;
	public Notification loadNotificationByORMID(int ID, LockMode lockMode) throws PersistentException;
	public Notification getNotificationByORMID(int ID, LockMode lockMode) throws PersistentException;
	public Notification loadNotificationByORMID(PersistentSession session, int ID) throws PersistentException;
	public Notification getNotificationByORMID(PersistentSession session, int ID) throws PersistentException;
	public Notification loadNotificationByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public Notification getNotificationByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public Notification[] listNotificationByQuery(String condition, String orderBy) throws PersistentException;
	public Notification[] listNotificationByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryNotification(String condition, String orderBy) throws PersistentException;
	public java.util.List queryNotification(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateNotificationByQuery(String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateNotificationByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Notification[] listNotificationByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public Notification[] listNotificationByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryNotification(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.List queryNotification(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateNotificationByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateNotificationByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Notification loadNotificationByQuery(String condition, String orderBy) throws PersistentException;
	public Notification loadNotificationByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Notification loadNotificationByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public Notification loadNotificationByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Notification createNotification();
	public boolean save(Notification notification) throws PersistentException;
	public boolean delete(Notification notification) throws PersistentException;
	public boolean deleteAndDissociate(Notification notification) throws PersistentException;
	public boolean deleteAndDissociate(Notification notification, PersistentSession session) throws PersistentException;
	public boolean refresh(Notification notification) throws PersistentException;
	public boolean evict(Notification notification) throws PersistentException;
	public Notification loadNotificationByCriteria(NotificationCriteria notificationCriteria);
	public Notification[] listNotificationByCriteria(NotificationCriteria notificationCriteria);
}
