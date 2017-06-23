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
import org.hibernate.LockMode;

public interface GroupStudentDAO {
	boolean exists(int id) throws PersistentException;
	boolean exists(int groupID, int studentID) throws PersistentException;
	GroupStudent loadGroupStudentByGroupAndStudent(int groupID, int studentID) throws PersistentException;


	public GroupStudent loadGroupStudentByORMID(int ID) throws PersistentException;
	public GroupStudent getGroupStudentByORMID(int ID) throws PersistentException;
	public GroupStudent loadGroupStudentByORMID(int ID, LockMode lockMode) throws PersistentException;
	public GroupStudent getGroupStudentByORMID(int ID, LockMode lockMode) throws PersistentException;
	public GroupStudent loadGroupStudentByORMID(PersistentSession session, int ID) throws PersistentException;
	public GroupStudent getGroupStudentByORMID(PersistentSession session, int ID) throws PersistentException;
	public GroupStudent loadGroupStudentByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public GroupStudent getGroupStudentByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public GroupStudent[] listGroupStudentByQuery(String condition, String orderBy) throws PersistentException;
	public GroupStudent[] listGroupStudentByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryGroupStudent(String condition, String orderBy) throws PersistentException;
	public java.util.List queryGroupStudent(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateGroupStudentByQuery(String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateGroupStudentByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public GroupStudent[] listGroupStudentByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public GroupStudent[] listGroupStudentByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryGroupStudent(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.List queryGroupStudent(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateGroupStudentByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateGroupStudentByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public GroupStudent loadGroupStudentByQuery(String condition, String orderBy) throws PersistentException;
	public GroupStudent loadGroupStudentByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public GroupStudent loadGroupStudentByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public GroupStudent loadGroupStudentByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public GroupStudent createGroupStudent();
	public boolean save(GroupStudent groupStudent) throws PersistentException;
	public boolean delete(GroupStudent groupStudent) throws PersistentException;
	public boolean deleteAndDissociate(GroupStudent groupStudent) throws PersistentException;
	public boolean deleteAndDissociate(GroupStudent groupStudent, PersistentSession session) throws PersistentException;
	public boolean refresh(GroupStudent groupStudent) throws PersistentException;
	public boolean evict(GroupStudent groupStudent) throws PersistentException;
	public GroupStudent loadGroupStudentByCriteria(GroupStudentCriteria groupStudentCriteria);
	public GroupStudent[] listGroupStudentByCriteria(GroupStudentCriteria groupStudentCriteria);
}
