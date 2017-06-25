package model.persistent; /**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

import dao.QuestionScoreSetCollection;
import dao.SubmissionSetCollection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Licensee: Universidade do Minho
 * License Type: Academic
 */
public class Exam {
	public String getBeginDateAsString() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return df.format(this.getBeginDate());
	}

	public String getDurationAsString() {
		DateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(this.getDuration());
	}

	public Exam() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_EXAM__SUBMISSIONS) {
			return ORM__submissions;
		}
		else if (key == ORMConstants.KEY_EXAM__QUESTIONS) {
			return ORM__questions;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_EXAM__GROUP) {
			this._group = (Group) owner;
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
	
	private Group _group;
	
	private java.sql.Timestamp beginDate;
	
	private java.sql.Time duration;
	
	private String name;
	
	private java.util.Set ORM__submissions = new java.util.HashSet();
	
	private java.util.Set ORM__questions = new java.util.HashSet();
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setBeginDate(java.sql.Timestamp value) {
		this.beginDate = value;
	}
	
	public java.sql.Timestamp getBeginDate() {
		return beginDate;
	}
	
	public void setDuration(java.sql.Time value) {
		this.duration = value;
	}
	
	public java.sql.Time getDuration() {
		return duration;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void set_group(Group value) {
		if (_group != null) {
			_group._exams.remove(this);
		}
		if (value != null) {
			value._exams.add(this);
		}
	}
	
	public Group get_group() {
		return _group;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM__group(Group value) {
		this._group = value;
	}
	
	private Group getORM__group() {
		return _group;
	}
	
	private void setORM__submissions(java.util.Set value) {
		this.ORM__submissions = value;
	}
	
	private java.util.Set getORM__submissions() {
		return ORM__submissions;
	}
	
	public final SubmissionSetCollection _submissions = new SubmissionSetCollection(this, _ormAdapter, ORMConstants.KEY_EXAM__SUBMISSIONS, ORMConstants.KEY_SUBMISSION__EXAM, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM__questions(java.util.Set value) {
		this.ORM__questions = value;
	}
	
	private java.util.Set getORM__questions() {
		return ORM__questions;
	}
	
	public final QuestionScoreSetCollection _questions = new QuestionScoreSetCollection(this, _ormAdapter, ORMConstants.KEY_EXAM__QUESTIONS, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
