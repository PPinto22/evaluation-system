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
import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class StudentCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression email;
	public final StringExpression password;
	public final StringExpression firstName;
	public final StringExpression lastName;
	public final BooleanExpression registered;
	public final BooleanExpression deleted;
	public final StringExpression registrationCode;
	public final CollectionExpression _notifications;
	public final CollectionExpression _submissions;
	public final CollectionExpression _groups;
	
	public StudentCriteria(Criteria criteria) {
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
		_submissions = new CollectionExpression("ORM__submissions", this);
		_groups = new CollectionExpression("ORM__groups", this);
	}
	
	public StudentCriteria(PersistentSession session) {
		this(session.createCriteria(Student.class));
	}
	
	public StudentCriteria() throws PersistentException {
		this(ClassesPersistentManager.instance().getSession());
	}
	
	public SubmissionCriteria create_submissionsCriteria() {
		return new SubmissionCriteria(createCriteria("ORM__submissions"));
	}
	
	public GroupStudentCriteria create_groupsCriteria() {
		return new GroupStudentCriteria(createCriteria("ORM__groups"));
	}
	
	public NotificationCriteria create_notificationsCriteria() {
		return new NotificationCriteria(createCriteria("ORM__notifications"));
	}
	
	public Student uniqueStudent() {
		return (Student) super.uniqueResult();
	}
	
	public Student[] listStudent() {
		java.util.List list = super.list();
		return (Student[]) list.toArray(new Student[list.size()]);
	}
}

