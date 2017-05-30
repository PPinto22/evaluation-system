package model; /**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

import dao.GroupStudentSetCollection;
import dao.ORMConstants;
import dao.SubmissionSetCollection;

/**
 * Licensee: Universidade do Minho
 * License Type: Academic
 */
public class Student extends User {
	public Student() {
	}

	public Student(User user) {
		super(user);
	}

    private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_STUDENT__SUBMISSIONS) {
			return ORM__submissions;
		}
		else if (key == ORMConstants.KEY_STUDENT__GROUPS) {
			return ORM__groups;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private java.util.Set ORM__submissions = new java.util.HashSet();
	
	private java.util.Set ORM__groups = new java.util.HashSet();
	
	private void setORM__submissions(java.util.Set value) {
		this.ORM__submissions = value;
	}
	
	private java.util.Set getORM__submissions() {
		return ORM__submissions;
	}
	
	public final SubmissionSetCollection _submissions = new SubmissionSetCollection(this, _ormAdapter, ORMConstants.KEY_STUDENT__SUBMISSIONS, ORMConstants.KEY_SUBMISSION__STUDENT, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM__groups(java.util.Set value) {
		this.ORM__groups = value;
	}
	
	private java.util.Set getORM__groups() {
		return ORM__groups;
	}
	
	public final GroupStudentSetCollection _groups = new GroupStudentSetCollection(this, _ormAdapter, ORMConstants.KEY_STUDENT__GROUPS, ORMConstants.KEY_GROUPSTUDENT__STUDENT, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return super.toString();
	}
	
}
