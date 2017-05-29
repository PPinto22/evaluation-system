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
public class Notification {
	public Notification() {
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_NOTIFICATION__USER) {
			this._user = (User) owner;
		}
	}
	
	public org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int ID;
	
	private User _user;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void set_user(User value) {
		if (_user != null) {
			_user._notifications.remove(this);
		}
		if (value != null) {
			value._notifications.add(this);
		}
	}
	
	public User get_user() {
		return _user;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM__user(User value) {
		this._user = value;
	}
	
	private User getORM__user() {
		return _user;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
