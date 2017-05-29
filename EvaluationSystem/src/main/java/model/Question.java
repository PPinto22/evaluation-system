package model; /**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

import dao.AnswerSetCollection;
import dao.ORMConstants;

/**
 * Licensee: Universidade do Minho
 * License Type: Academic
 */
public class Question {
	public Question() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_QUESTION__ANSWERS) {
			return ORM__answers;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_QUESTION__CLASS) {
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
	
	private String text;
	
	private String category;
	
	private int dificulty;
	
	private java.util.Set ORM__answers = new java.util.HashSet();
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setText(String value) {
		this.text = value;
	}
	
	public String getText() {
		return text;
	}
	
	public void setCategory(String value) {
		this.category = value;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setDificulty(int value) {
		this.dificulty = value;
	}
	
	public int getDificulty() {
		return dificulty;
	}
	
	public void set_class(Class value) {
		if (_class != null) {
			_class._question.remove(this);
		}
		if (value != null) {
			value._question.add(this);
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
	
	private void setORM__answers(java.util.Set value) {
		this.ORM__answers = value;
	}
	
	private java.util.Set getORM__answers() {
		return ORM__answers;
	}
	
	public final AnswerSetCollection _answers = new AnswerSetCollection(this, _ormAdapter, ORMConstants.KEY_QUESTION__ANSWERS, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
