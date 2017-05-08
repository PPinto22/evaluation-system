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
public class Answer {
	public Answer() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_ANSWER__QUESTIONSUBMISSION) {
			return ORM__questionSubmission;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int ID;
	
	private String text;
	
	private boolean correct;
	
	private java.util.Set ORM__questionSubmission = new java.util.HashSet();
	
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
	
	public void setCorrect(boolean value) {
		this.correct = value;
	}
	
	public boolean getCorrect() {
		return correct;
	}
	
	private void setORM__questionSubmission(java.util.Set value) {
		this.ORM__questionSubmission = value;
	}
	
	private java.util.Set getORM__questionSubmission() {
		return ORM__questionSubmission;
	}
	
	public final QuestionSubmissionSetCollection _questionSubmission = new QuestionSubmissionSetCollection(this, _ormAdapter, ORMConstants.KEY_ANSWER__QUESTIONSUBMISSION, ORMConstants.KEY_QUESTIONSUBMISSION__ANSWER, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
