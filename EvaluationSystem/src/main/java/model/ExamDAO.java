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

public interface ExamDAO {
	public Exam loadExamByORMID(int ID) throws PersistentException;
	public Exam getExamByORMID(int ID) throws PersistentException;
	public Exam loadExamByORMID(int ID, LockMode lockMode) throws PersistentException;
	public Exam getExamByORMID(int ID, LockMode lockMode) throws PersistentException;
	public Exam loadExamByORMID(PersistentSession session, int ID) throws PersistentException;
	public Exam getExamByORMID(PersistentSession session, int ID) throws PersistentException;
	public Exam loadExamByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public Exam getExamByORMID(PersistentSession session, int ID, LockMode lockMode) throws PersistentException;
	public Exam[] listExamByQuery(String condition, String orderBy) throws PersistentException;
	public Exam[] listExamByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryExam(String condition, String orderBy) throws PersistentException;
	public java.util.List queryExam(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateExamByQuery(String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateExamByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Exam[] listExamByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public Exam[] listExamByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.List queryExam(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.List queryExam(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public java.util.Iterator iterateExamByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public java.util.Iterator iterateExamByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Exam loadExamByQuery(String condition, String orderBy) throws PersistentException;
	public Exam loadExamByQuery(String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Exam loadExamByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException;
	public Exam loadExamByQuery(PersistentSession session, String condition, String orderBy, LockMode lockMode) throws PersistentException;
	public Exam createExam();
	public boolean save(Exam exam) throws PersistentException;
	public boolean delete(Exam exam) throws PersistentException;
	public boolean deleteAndDissociate(Exam exam) throws PersistentException;
	public boolean deleteAndDissociate(Exam exam, PersistentSession session) throws PersistentException;
	public boolean refresh(Exam exam) throws PersistentException;
	public boolean evict(Exam exam) throws PersistentException;
	public Exam loadExamByCriteria(ExamCriteria examCriteria);
	public Exam[] listExamByCriteria(ExamCriteria examCriteria);
}
