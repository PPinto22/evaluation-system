package model; /**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

import dao.ORMConstants;

/**
 * Licensee: Universidade do Minho
 * License Type: Academic
 */
public class GroupStudent {
	public GroupStudent() {
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_GROUPSTUDENT__STUDENT) {
			this._student = (Student) owner;
		}
		
		else if (key == ORMConstants.KEY_GROUPSTUDENT__GROUP) {
			this._group = (Group) owner;
		}
	}
	
	public org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int ID;
	
	private Student _student;
	
	private Group _group;
	
	private boolean accepted;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setAccepted(boolean value) {
		this.accepted = value;
	}
	
	public boolean isAccepted() {
		return accepted;
	}
	
	public void set_student(Student value) {
		if (_student != null) {
			_student._groups.remove(this);
		}
		if (value != null) {
			value._groups.add(this);
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
	
	public void set_group(Group value) {
		if (_group != null) {
			_group._students.remove(this);
		}
		if (value != null) {
			value._students.add(this);
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
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
