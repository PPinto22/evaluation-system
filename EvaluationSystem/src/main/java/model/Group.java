package model; /**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

import dao.ExamSetCollection;
import dao.GroupStudentSetCollection;
import dao.ORMConstants;

/**
 * Licensee: Universidade do Minho
 * License Type: Academic
 */
public class Group {

	public Group() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_GROUP__STUDENTS) {
			return ORM__students;
		}
		else if (key == ORMConstants.KEY_GROUP__EXAMS) {
			return ORM__exams;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_GROUP__CLASS) {
			this._class = (Class) owner;
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
	
	private Class _class;
	
	private String name;
	
	private java.util.Set ORM__students = new java.util.HashSet();
	
	private java.util.Set ORM__exams = new java.util.HashSet();
	
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
	
	private void setORM__students(java.util.Set value) {
		this.ORM__students = value;
	}
	
	private java.util.Set getORM__students() {
		return ORM__students;
	}
	
	public final GroupStudentSetCollection _students = new GroupStudentSetCollection(this, _ormAdapter, ORMConstants.KEY_GROUP__STUDENTS, ORMConstants.KEY_GROUPSTUDENT__GROUP, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public void set_class(Class value) {
		if (_class != null) {
			_class._groups.remove(this);
		}
		if (value != null) {
			value._groups.add(this);
		}
	}
	
	public Class get_class() {
		return _class;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM__class(Class value) {
		this._class = value;
	}
	
	private Class getORM__class() {
		return _class;
	}
	
	private void setORM__exams(java.util.Set value) {
		this.ORM__exams = value;
	}
	
	private java.util.Set getORM__exams() {
		return ORM__exams;
	}
	
	public final ExamSetCollection _exams = new ExamSetCollection(this, _ormAdapter, ORMConstants.KEY_GROUP__EXAMS, ORMConstants.KEY_EXAM__GROUP, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public float getAverageScore() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public int getApprovalRate() {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public void addStudent(Student student) {
		//TODO: Implement Method
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
