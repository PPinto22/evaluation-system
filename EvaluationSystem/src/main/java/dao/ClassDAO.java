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
import exception.NonExistentEntityException;
import model.persistent.Class;
import org.orm.*;
import org.hibernate.LockMode;

public interface ClassDAO {

	//Nosso codigo
	boolean exists(int ID) throws PersistentException;
	boolean exists(int teacherID, String className) throws PersistentException;
	Class getClassByName(int teacherID, String className) throws PersistentException, NonExistentEntityException;

	//Codigo gerado
	public Class loadClassByORMID(int ID) throws PersistentException;
	public Class getClassByORMID(int ID) throws PersistentException;
	public Class loadClassByORMID(int ID, LockMode lockMode) throws PersistentException;
	public Class getClassByORMID(int ID, LockMode lockMode) throws PersistentException;
	public Class loadClassByORMID(PersistentSession session, int ID) throws PersistentException;
	public Class getClassByORMID(PersistentSession session, int ID) throws PersistentException;
	public Class loadClassByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public Class getClassByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public Class[] listClassByQuery(String condition, String orderBy) throws PersistentException;
	public Class[] listClassByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryClass(String condition, String orderBy) throws PersistentException;
	public java.util.List queryClass(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateClassByQuery(String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateClassByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Class[] listClassByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public Class[] listClassByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryClass(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.List queryClass(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateClassByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateClassByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Class loadClassByQuery(String condition, String orderBy) throws PersistentException;
	public Class loadClassByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Class loadClassByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public Class loadClassByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Class createClass();
	public boolean save(Class class1) throws PersistentException;
	public boolean delete(Class class1) throws PersistentException;
	public boolean deleteAndDissociate(Class class1) throws PersistentException;
	public boolean deleteAndDissociate(Class class1, PersistentSession session) throws PersistentException;
	public boolean refresh(Class class1) throws PersistentException;
	public boolean evict(Class class1) throws PersistentException;
	public Class loadClassByCriteria(ClassCriteria classCriteria);
	public Class[] listClassByCriteria(ClassCriteria classCriteria);
}
