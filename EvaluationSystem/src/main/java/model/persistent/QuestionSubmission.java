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
public class QuestionSubmission {
	public QuestionSubmission() {
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_QUESTIONSUBMISSION__SUBMISSION) {
			this._submission = (Submission) owner;
		}
		
		else if (key == ORMConstants.KEY_QUESTIONSUBMISSION__QUESTION) {
			this._question = (QuestionScore) owner;
		}
		
		else if (key == ORMConstants.KEY_QUESTIONSUBMISSION__ANSWER) {
			this._answer = (Answer) owner;
		}
	}
	
	public org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int ID;
	
	private Answer _answer;
	
	private Submission _submission;
	
	private boolean correct;
	
	private QuestionScore _question;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setCorrect(boolean value) {
		this.correct = value;
	}
	
	public boolean isCorrect() {
		return correct;
	}
	
	public void set_submission(Submission value) {
		if (_submission != null) {
			_submission._questionSubmissions.remove(this);
		}
		if (value != null) {
			value._questionSubmissions.add(this);
		}
	}
	
	public Submission get_submission() {
		return _submission;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM__submission(Submission value) {
		this._submission = value;
	}
	
	private Submission getORM__submission() {
		return _submission;
	}
	
	public void set_question(QuestionScore value) {
		this._question = value;
	}
	
	public QuestionScore get_question() {
		return _question;
	}
	
	public void set_answer(Answer value) {
		if (_answer != null) {
			_answer._questionSubmission.remove(this);
		}
		if (value != null) {
			value._questionSubmission.add(this);
		}
	}
	
	public Answer get_answer() {
		return _answer;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM__answer(Answer value) {
		this._answer = value;
	}
	
	private Answer getORM__answer() {
		return _answer;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
