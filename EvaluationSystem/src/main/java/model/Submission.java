package model; /**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

import dao.QuestionSubmissionSetCollection;

/**
 * Licensee: Universidade do Minho
 * License Type: Academic
 */
public class Submission {

	public Submission() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_SUBMISSION__QUESTIONSUBMISSIONS) {
			return ORM__questionSubmissions;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_SUBMISSION__STUDENT) {
			this._student = (Student) owner;
		}
		
		else if (key == ORMConstants.KEY_SUBMISSION__EXAM) {
			this._exam = (Exam) owner;
		}
	}
	
	public org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int ID;
	
	private Exam _exam;
	
	private Student _student;
	
	private float score;
	
	private java.util.Set ORM__questionSubmissions = new java.util.HashSet();
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setScore(float value) {
		this.score = value;
	}
	
	public float getScore() {
		return score;
	}
	
	public void set_student(Student value) {
		if (_student != null) {
			_student._submissions.remove(this);
		}
		if (value != null) {
			value._submissions.add(this);
		}
	}
	
	public Student get_student() {
		return _student;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM__student(Student value) {
		this._student = value;
	}
	
	private Student getORM__student() {
		return _student;
	}
	
	public void set_exam(Exam value) {
		if (_exam != null) {
			_exam._submissions.remove(this);
		}
		if (value != null) {
			value._submissions.add(this);
		}
	}
	
	public Exam get_exam() {
		return _exam;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM__exam(Exam value) {
		this._exam = value;
	}
	
	private Exam getORM__exam() {
		return _exam;
	}
	
	private void setORM__questionSubmissions(java.util.Set value) {
		this.ORM__questionSubmissions = value;
	}
	
	private java.util.Set getORM__questionSubmissions() {
		return ORM__questionSubmissions;
	}
	
	public final QuestionSubmissionSetCollection _questionSubmissions = new QuestionSubmissionSetCollection(this, _ormAdapter, ORMConstants.KEY_SUBMISSION__QUESTIONSUBMISSIONS, ORMConstants.KEY_QUESTIONSUBMISSION__SUBMISSION, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
