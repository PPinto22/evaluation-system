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
import model.Group;
import model.Class;
import org.orm.*;
import org.hibernate.LockMode;

public interface GroupDAO {
	boolean exists(PersistentSession session, int ID) throws PersistentException;
	boolean exists(PersistentSession session, Class cl, String name) throws PersistentException;
	Group loadGroupByName(PersistentSession session, Class cl, String name) throws PersistentException;

	public Group loadGroupByORMID(int ID) throws PersistentException;
	public Group getGroupByORMID(int ID) throws PersistentException;
	public Group loadGroupByORMID(int ID, LockMode lockMode) throws PersistentException;
	public Group getGroupByORMID(int ID, LockMode lockMode) throws PersistentException;
	public Group loadGroupByORMID(PersistentSession session, int ID) throws PersistentException;
	public Group getGroupByORMID(PersistentSession session, int ID) throws PersistentException;
	public Group loadGroupByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public Group getGroupByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public Group[] listGroupByQuery(String condition, String orderBy) throws PersistentException;
	public Group[] listGroupByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryGroup(String condition, String orderBy) throws PersistentException;
	public java.util.List queryGroup(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateGroupByQuery(String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateGroupByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Group[] listGroupByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public Group[] listGroupByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryGroup(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.List queryGroup(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateGroupByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateGroupByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Group loadGroupByQuery(String condition, String orderBy) throws PersistentException;
	public Group loadGroupByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Group loadGroupByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public Group loadGroupByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Group createGroup();
	public boolean save(Group group) throws PersistentException;
	public boolean delete(Group group) throws PersistentException;
	public boolean deleteAndDissociate(Group group) throws PersistentException;
	public boolean deleteAndDissociate(Group group, PersistentSession session) throws PersistentException;
	public boolean refresh(Group group) throws PersistentException;
	public boolean evict(Group group) throws PersistentException;
	public Group loadGroupByCriteria(GroupCriteria groupCriteria);
	public Group[] listGroupByCriteria(GroupCriteria groupCriteria);
}
