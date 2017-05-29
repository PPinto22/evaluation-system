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
import model.Teacher;
import org.orm.*;
import org.hibernate.LockMode;

public interface TeacherDAO {
	public Teacher loadTeacherByORMID(int ID) throws PersistentException;
	public Teacher getTeacherByORMID(int ID) throws PersistentException;
	public Teacher loadTeacherByORMID(int ID, LockMode lockMode) throws PersistentException;
	public Teacher getTeacherByORMID(int ID, LockMode lockMode) throws PersistentException;
	public Teacher loadTeacherByORMID(PersistentSession session, int ID) throws PersistentException;
	public Teacher getTeacherByORMID(PersistentSession session, int ID) throws PersistentException;
	public Teacher loadTeacherByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public Teacher getTeacherByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public Teacher[] listTeacherByQuery(String condition, String orderBy) throws PersistentException;
	public Teacher[] listTeacherByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryTeacher(String condition, String orderBy) throws PersistentException;
	public java.util.List queryTeacher(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateTeacherByQuery(String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateTeacherByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Teacher[] listTeacherByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public Teacher[] listTeacherByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryTeacher(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.List queryTeacher(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateTeacherByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateTeacherByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Teacher loadTeacherByQuery(String condition, String orderBy) throws PersistentException;
	public Teacher loadTeacherByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Teacher loadTeacherByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public Teacher loadTeacherByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Teacher createTeacher();
	public boolean save(Teacher teacher) throws PersistentException;
	public boolean delete(Teacher teacher) throws PersistentException;
	public boolean deleteAndDissociate(Teacher teacher) throws PersistentException;
	public boolean deleteAndDissociate(Teacher teacher, PersistentSession session) throws PersistentException;
	public boolean refresh(Teacher teacher) throws PersistentException;
	public boolean evict(Teacher teacher) throws PersistentException;
	public Teacher loadTeacherByCriteria(TeacherCriteria teacherCriteria);
	public Teacher[] listTeacherByCriteria(TeacherCriteria teacherCriteria);
}
