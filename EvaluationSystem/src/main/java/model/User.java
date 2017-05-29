package model; /**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

import dao.NotificationSetCollection;
import dao.ORMConstants;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Licensee: Universidade do Minho
 * License Type: Academic
 */
public class User {
	public User() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_USER__NOTIFICATIONS) {
			return ORM__notifications;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int ID;
	
	private String email;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private boolean registered;
	
	private boolean deleted;
	
	private String registrationCode;
	
	private java.util.Set ORM__notifications = new java.util.HashSet();
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}

	public int getORMID() {
		return getID();
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String value) {
		this.password = this.encrypt(value);
	}

	public String getPassword() {
		return password;
	}
	
	public void setFirstName(String value) {
		this.firstName = value;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String value) {
		this.lastName = value;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setRegistered(boolean value) {
		this.registered = value;
	}

	public boolean getRegistered() {
		return registered;
	}
	
	public void setDeleted(boolean value) {
		this.deleted = value;
	}

	public boolean getDeleted() {
		return deleted;
	}
	
	public void setRegistrationCode(String value) {
		this.registrationCode = value;
	}

	public String getRegistrationCode() {
		return registrationCode;
	}
	
	private void setORM__notifications(java.util.Set value) {
		this.ORM__notifications = value;
	}
	
	private java.util.Set getORM__notifications() {
		return ORM__notifications;
	}
	
	public final NotificationSetCollection _notifications = new NotificationSetCollection(this, _ormAdapter, ORMConstants.KEY_USER__NOTIFICATIONS, ORMConstants.KEY_NOTIFICATION__USER, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getID());
	}

	private String encrypt(String data){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		try {
			md.update(data.getBytes("UTF-8")); // Change this to "UTF-16" if needed
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] digest = md.digest();
		return new String(digest);
	}
	
}
