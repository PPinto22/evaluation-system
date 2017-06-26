package dao; /**
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
import java.util.List;

import model.persistent.Notification;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class NotificationDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression _userId;
	public final AssociationExpression _user;
	
	public NotificationDetachedCriteria() {
		super(Notification.class, NotificationCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_userId = new IntegerExpression("_user.ID", this.getDetachedCriteria());
		_user = new AssociationExpression("_user", this.getDetachedCriteria());
	}
	
	public NotificationDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, NotificationCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_userId = new IntegerExpression("_user.ID", this.getDetachedCriteria());
		_user = new AssociationExpression("_user", this.getDetachedCriteria());
	}
	
	public UserDetachedCriteria create_userCriteria() {
		return new UserDetachedCriteria(createCriteria("_user"));
	}
	
	public Notification uniqueNotification(PersistentSession session) {
		return (Notification) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Notification[] listNotification(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Notification[]) list.toArray(new Notification[list.size()]);
	}
}

