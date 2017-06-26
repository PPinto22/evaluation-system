package model.persistent; /**
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
public class QuestionScore {
	public QuestionScore() {
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_QUESTIONSCORE__EXAM) {
			this._exam = (Exam) owner;
		}
		
		else if (key == ORMConstants.KEY_QUESTIONSCORE__QUESTION) {
			this._question = (Question) owner;
		}
	}
	
	public org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int ID;
	
	private Question _question;
	
	private Exam _exam;
	
	private float score;
	
	private int order;
	
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
	
	public void setOrder(int value) {
		this.order = value;
	}
	
	public int getOrder() {
		return order;
	}
	
	public void set_exam(Exam value) {
		if (_exam != null) {
			_exam._questions.remove(this);
		}
		if (value != null) {
			value._questions.add(this);
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
	
	public void set_question(Question value) {
		this._question = value;
	}
	
	public Question get_question() {
		return _question;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
