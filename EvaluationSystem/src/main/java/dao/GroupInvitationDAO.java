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
import model.GroupInvitation;
import model.Student;
import org.orm.*;
import org.hibernate.LockMode;

public interface GroupInvitationDAO {
	public GroupInvitation loadGroupInvitationByGroupAndStudent(Group group, Student student) throws PersistentException;

	public GroupInvitation loadGroupInvitationByORMID(int ID) throws PersistentException;
	public GroupInvitation getGroupInvitationByORMID(int ID) throws PersistentException;
	public GroupInvitation loadGroupInvitationByORMID(int ID, LockMode lockMode) throws PersistentException;
	public GroupInvitation getGroupInvitationByORMID(int ID, LockMode lockMode) throws PersistentException;
	public GroupInvitation loadGroupInvitationByORMID(PersistentSession session, int ID) throws PersistentException;
	public GroupInvitation getGroupInvitationByORMID(PersistentSession session, int ID) throws PersistentException;
	public GroupInvitation loadGroupInvitationByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public GroupInvitation getGroupInvitationByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public GroupInvitation[] listGroupInvitationByQuery(String condition, String orderBy) throws PersistentException;
	public GroupInvitation[] listGroupInvitationByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryGroupInvitation(String condition, String orderBy) throws PersistentException;
	public java.util.List queryGroupInvitation(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateGroupInvitationByQuery(String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateGroupInvitationByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public GroupInvitation[] listGroupInvitationByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public GroupInvitation[] listGroupInvitationByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryGroupInvitation(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.List queryGroupInvitation(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateGroupInvitationByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateGroupInvitationByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public GroupInvitation loadGroupInvitationByQuery(String condition, String orderBy) throws PersistentException;
	public GroupInvitation loadGroupInvitationByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public GroupInvitation loadGroupInvitationByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public GroupInvitation loadGroupInvitationByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public GroupInvitation createGroupInvitation();
	public boolean save(GroupInvitation groupInvitation) throws PersistentException;
	public boolean delete(GroupInvitation groupInvitation) throws PersistentException;
	public boolean deleteAndDissociate(GroupInvitation groupInvitation) throws PersistentException;
	public boolean deleteAndDissociate(GroupInvitation groupInvitation, PersistentSession session) throws PersistentException;
	public boolean refresh(GroupInvitation groupInvitation) throws PersistentException;
	public boolean evict(GroupInvitation groupInvitation) throws PersistentException;
	public GroupInvitation loadGroupInvitationByCriteria(GroupInvitationCriteria groupInvitationCriteria);
	public GroupInvitation[] listGroupInvitationByCriteria(GroupInvitationCriteria groupInvitationCriteria);
}
