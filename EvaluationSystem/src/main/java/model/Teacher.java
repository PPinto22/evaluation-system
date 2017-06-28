package model; /**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

import dao.ClassSetCollection;

/**
 * Licensee: Universidade do Minho
 * License Type: Academic
 */
public class Teacher extends User {
	public Teacher() {
	}

    public Teacher(User user) {
		super(user);
    }

    private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_TEACHER__CLASSES) {
			return ORM__classes;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private java.util.Set ORM__classes = new java.util.HashSet();
	
	private void setORM__classes(java.util.Set value) {
		this.ORM__classes = value;
	}
	
	private java.util.Set getORM__classes() {
		return ORM__classes;
	}
	
	public final ClassSetCollection _classes = new ClassSetCollection(this, _ormAdapter, ORMConstants.KEY_TEACHER__CLASSES, ORMConstants.KEY_CLASS__TEACHER, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return super.toString();
	}
	
}
