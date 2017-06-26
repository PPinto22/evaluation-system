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
import model.persistent.Teacher;
import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class TeacherCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression email;
	public final StringExpression password;
	public final StringExpression firstName;
	public final StringExpression lastName;
	public final BooleanExpression registered;
	public final BooleanExpression deleted;
	public final StringExpression registrationCode;
	public final CollectionExpression _notifications;
	public final CollectionExpression _classes;
	
	public TeacherCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		email = new StringExpression("email", this);
		password = new StringExpression("password", this);
		firstName = new StringExpression("firstName", this);
		lastName = new StringExpression("lastName", this);
		registered = new BooleanExpression("registered", this);
		deleted = new BooleanExpression("deleted", this);
		registrationCode = new StringExpression("registrationCode", this);
		_notifications = new CollectionExpression("ORM__notifications", this);
		_classes = new CollectionExpression("ORM__classes", this);
	}
	
	public TeacherCriteria(PersistentSession session) {
		this(session.createCriteria(Teacher.class));
	}
	
	public TeacherCriteria() throws PersistentException {
		this(ClassesPersistentManager.instance().getSession());
	}
	
	public ClassCriteria create_classesCriteria() {
		return new ClassCriteria(createCriteria("ORM__classes"));
	}
	
	public NotificationCriteria create_notificationsCriteria() {
		return new NotificationCriteria(createCriteria("ORM__notifications"));
	}
	
	public Teacher uniqueTeacher() {
		return (Teacher) super.uniqueResult();
	}
	
	public Teacher[] listTeacher() {
		java.util.List list = super.list();
		return (Teacher[]) list.toArray(new Teacher[list.size()]);
	}
}

