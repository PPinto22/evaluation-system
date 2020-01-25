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
import exception.InvalidUserException;
import exception.UnconfirmedRegistrationException;
import model.Notification;
import model.Student;
import model.Teacher;
import model.User;
import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

	@Override
	public User loadUserByAuthentication(PersistentSession session, String email, String password) throws PersistentException, InvalidUserException, UnconfirmedRegistrationException {
		UserCriteria criteria = new UserCriteria(session);
		criteria.email.eq(email);
		criteria.password.eq(password);
		criteria.deleted.eq(false);
		User[] users = this.listUserByCriteria(criteria);
		if(users.length == 0){
			throw new InvalidUserException();
		}
		for(int i = 0; i<users.length; i++){
			if(users[i].isRegistered())
				return users[i];
		}
		throw new UnconfirmedRegistrationException();
	}

	@Override
	public List<User> loadUsersByEmail(PersistentSession session, String email) throws PersistentException {
		UserCriteria criteria = new UserCriteria(session);
		criteria.email.eq(email);
		criteria.deleted.eq(false);
		return Arrays.asList(this.listUserByCriteria(criteria));
	}

	@Override
	public boolean exists(PersistentSession session, String email) throws PersistentException {
		UserCriteria criteria = new UserCriteria(session);
		criteria.email.eq(email);
		criteria.deleted.eq(false);
		return this.loadUserByCriteria(criteria) != null;
	}

	@Override
	public boolean exists(PersistentSession session, int ID) throws PersistentException {
		UserCriteria criteria = new UserCriteria(session);
		criteria.ID.eq(ID);
		criteria.deleted.eq(false);
		return this.loadUserByCriteria(criteria) != null;
	}

	@Override
	public boolean existsActive(PersistentSession session, String email) throws PersistentException {
		UserCriteria criteria = new UserCriteria(session);
		criteria.email.eq(email);
		criteria.deleted.eq(false);
		criteria.registered.eq(true);
		return this.loadUserByCriteria(criteria) != null;
	}

	private static final org.apache.log4j.Logger _logger = org.apache.log4j.Logger.getLogger(UserDAOImpl.class);
	public User loadUserByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadUserByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("loadUserByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public User getUserByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getUserByORMID(session, ID);
		}
		catch (Exception e) {
			_logger.error("getUserByORMID(int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public User loadUserByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadUserByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadUserByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public User getUserByORMID(int ID, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return getUserByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			_logger.error("getUserByORMID(int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public User loadUserByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (User) session.load(User.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("loadUserByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public User getUserByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (User) session.get(User.class, new Integer(ID));
		}
		catch (Exception e) {
			_logger.error("getUserByORMID(PersistentSession session, int ID)", e);
			throw new PersistentException(e);
		}
	}
	
	public User loadUserByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (User) session.load(User.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("loadUserByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public User getUserByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException {
		try {
			return (User) session.get(User.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			_logger.error("getUserByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryUser(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryUser(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("queryUser(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryUser(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return queryUser(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("queryUser(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public User[] listUserByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listUserByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("listUserByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public User[] listUserByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return listUserByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("listUserByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryUser(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.User as model.User");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listUserByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public List queryUser(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.User as model.User");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.User", lockMode);
			return query.list();
		}
		catch (Exception e) {
			_logger.error("listUserByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public User[] listUserByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryUser(session, condition, orderBy);
			return (User[]) list.toArray(new User[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listUserByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public User[] listUserByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			List list = queryUser(session, condition, orderBy, lockMode);
			return (User[]) list.toArray(new User[list.size()]);
		}
		catch (Exception e) {
			_logger.error("listUserByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public User loadUserByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadUserByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("loadUserByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public User loadUserByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return loadUserByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("loadUserByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public User loadUserByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		User[] users = listUserByQuery(session, condition, orderBy);
		if (users != null && users.length > 0)
			return users[0];
		else
			return null;
	}
	
	public User loadUserByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		User[] users = listUserByQuery(session, condition, orderBy, lockMode);
		if (users != null && users.length > 0)
			return users[0];
		else
			return null;
	}
	
	public java.util.Iterator iterateUserByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateUserByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			_logger.error("iterateUserByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateUserByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ClassesPersistentManager.instance().getSession();
			return iterateUserByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			_logger.error("iterateUserByQuery(String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateUserByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.User as model.User");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateUserByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public java.util.Iterator iterateUserByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From model.User as model.User");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("model.User", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			_logger.error("iterateUserByQuery(PersistentSession session, String condition, String orderBy)", e);
			throw new PersistentException(e);
		}
	}
	
	public User createUser() {
		return new User();
	}
	
	public boolean save(User user) throws PersistentException {
		try {
			ClassesPersistentManager.instance().saveObject(user);
			return true;
		}
		catch (Exception e) {
			_logger.error("save(model.User user)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean delete(User user) throws PersistentException {
		try {
			ClassesPersistentManager.instance().deleteObject(user);
			return true;
		}
		catch (Exception e) {
			_logger.error("delete(model.User user)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(User user)throws PersistentException {
		if (user instanceof Student) {
			return DAOFactory.getDAOFactory().getStudentDAO().deleteAndDissociate((Student) user);
		}
		
		if (user instanceof Teacher) {
			return DAOFactory.getDAOFactory().getTeacherDAO().deleteAndDissociate((Teacher) user);
		}
		
		try {
			Notification[] l_notificationss = user._notifications.toArray();
			for(int i = 0; i < l_notificationss.length; i++) {
				l_notificationss[i].set_user(null);
			}
			return delete(user);
		}
		catch(Exception e) {
			_logger.error("deleteAndDissociate()", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(User user, PersistentSession session)throws PersistentException {
		if (user instanceof Student) {
			return DAOFactory.getDAOFactory().getStudentDAO().deleteAndDissociate((Student) user, session);
		}
		
		if (user instanceof Teacher) {
			return DAOFactory.getDAOFactory().getTeacherDAO().deleteAndDissociate((Teacher) user, session);
		}
		
		try {
			Notification[] l_notificationss = user._notifications.toArray();
			for(int i = 0; i < l_notificationss.length; i++) {
				l_notificationss[i].set_user(null);
			}
			try {
				session.delete(user);
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
	
	public boolean refresh(User user) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().refresh(user);
			return true;
		}
		catch (Exception e) {
			_logger.error("refresh(model.User user)", e);
			throw new PersistentException(e);
		}
	}
	
	public boolean evict(User user) throws PersistentException {
		try {
			ClassesPersistentManager.instance().getSession().evict(user);
			return true;
		}
		catch (Exception e) {
			_logger.error("evict(model.User user)", e);
			throw new PersistentException(e);
		}
	}
	
	public User loadUserByCriteria(UserCriteria userCriteria) {
		User[] users = listUserByCriteria(userCriteria);
		if(users == null || users.length == 0) {
			return null;
		}
		return users[0];
	}
	
	public User[] listUserByCriteria(UserCriteria userCriteria) {
		return userCriteria.listUser();
	}
}
