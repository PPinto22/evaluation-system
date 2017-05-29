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
import org.hibernate.LockMode;

public interface StudentDAO {
	public Student loadStudentByORMID(int ID) throws PersistentException;
	public Student getStudentByORMID(int ID) throws PersistentException;
	public Student loadStudentByORMID(int ID, LockMode lockMode) throws PersistentException;
	public Student getStudentByORMID(int ID, LockMode lockMode) throws PersistentException;
	public Student loadStudentByORMID(PersistentSession session, int ID) throws PersistentException;
	public Student getStudentByORMID(PersistentSession session, int ID) throws PersistentException;
	public Student loadStudentByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public Student getStudentByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public Student[] listStudentByQuery(String condition, String orderBy) throws PersistentException;
	public Student[] listStudentByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryStudent(String condition, String orderBy) throws PersistentException;
	public java.util.List queryStudent(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateStudentByQuery(String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateStudentByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Student[] listStudentByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public Student[] listStudentByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryStudent(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.List queryStudent(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateStudentByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateStudentByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Student loadStudentByQuery(String condition, String orderBy) throws PersistentException;
	public Student loadStudentByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Student loadStudentByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public Student loadStudentByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Student createStudent();
	public boolean save(Student student) throws PersistentException;
	public boolean delete(Student student) throws PersistentException;
	public boolean deleteAndDissociate(Student student) throws PersistentException;
	public boolean deleteAndDissociate(Student student, PersistentSession session) throws PersistentException;
	public boolean refresh(Student student) throws PersistentException;
	public boolean evict(Student student) throws PersistentException;
	public Student loadStudentByCriteria(StudentCriteria studentCriteria);
	public Student[] listStudentByCriteria(StudentCriteria studentCriteria);
}
