package model; /**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

import dao.GroupSetCollection;
import dao.QuestionSetCollection;

/**
 * Licensee: Universidade do Minho
 * License Type: Academic
 */
public class Class {
	public boolean isMissingInformation(){
		return 	this.getName() == null || this.getName().equals("") ||
				this.getAbbreviation() == null || this.getAbbreviation().equals("");
	}

	public Class() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_CLASS__QUESTION) {
			return ORM__question;
		}
		else if (key == ORMConstants.KEY_CLASS__GROUPS) {
			return ORM__groups;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_CLASS__TEACHER) {
			this._teacher = (Teacher) owner;
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
	
	private Teacher _teacher;
	
	private String name;
	
	private String abbreviation;
	
	private java.util.Set ORM__question = new java.util.HashSet();
	
	private java.util.Set ORM__groups = new java.util.HashSet();
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setAbbreviation(String value) {
		this.abbreviation = value;
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}
	
	public void set_teacher(Teacher value) {
		if (_teacher != null) {
			_teacher._classes.remove(this);
		}
		if (value != null) {
			value._classes.add(this);
		}
	}
	
	public Teacher get_teacher() {
		return _teacher;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM__teacher(Teacher value) {
		this._teacher = value;
	}
	
	private Teacher getORM__teacher() {
		return _teacher;
	}
	
	private void setORM__question(java.util.Set value) {
		this.ORM__question = value;
	}
	
	private java.util.Set getORM__question() {
		return ORM__question;
	}
	
	public final QuestionSetCollection _question = new QuestionSetCollection(this, _ormAdapter, ORMConstants.KEY_CLASS__QUESTION, ORMConstants.KEY_QUESTION__CLASS, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM__groups(java.util.Set value) {
		this.ORM__groups = value;
	}
	
	private java.util.Set getORM__groups() {
		return ORM__groups;
	}
	
	public final GroupSetCollection _groups = new GroupSetCollection(this, _ormAdapter, ORMConstants.KEY_CLASS__GROUPS, ORMConstants.KEY_GROUP__CLASS, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
