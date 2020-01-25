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

import model.Student;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class StudentDetachedCriteria extends AbstractORMDetachedCriteria {
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
	
	public StudentDetachedCriteria() {
		super(Student.class, StudentCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		firstName = new StringExpression("firstName", this.getDetachedCriteria());
		lastName = new StringExpression("lastName", this.getDetachedCriteria());
		registered = new BooleanExpression("registered", this.getDetachedCriteria());
		deleted = new BooleanExpression("deleted", this.getDetachedCriteria());
		registrationCode = new StringExpression("registrationCode", this.getDetachedCriteria());
		_notifications = new CollectionExpression("ORM__notifications", this.getDetachedCriteria());
		_submissions = new CollectionExpression("ORM__submissions", this.getDetachedCriteria());
		_groups = new CollectionExpression("ORM__groups", this.getDetachedCriteria());
	}
	
	public StudentDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, StudentCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		email = new StringExpression("email", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		firstName = new StringExpression("firstName", this.getDetachedCriteria());
		lastName = new StringExpression("lastName", this.getDetachedCriteria());
		registered = new BooleanExpression("registered", this.getDetachedCriteria());
		deleted = new BooleanExpression("deleted", this.getDetachedCriteria());
		registrationCode = new StringExpression("registrationCode", this.getDetachedCriteria());
		_notifications = new CollectionExpression("ORM__notifications", this.getDetachedCriteria());
		_submissions = new CollectionExpression("ORM__submissions", this.getDetachedCriteria());
		_groups = new CollectionExpression("ORM__groups", this.getDetachedCriteria());
	}
	
	public SubmissionDetachedCriteria create_submissionsCriteria() {
		return new SubmissionDetachedCriteria(createCriteria("ORM__submissions"));
	}
	
	public GroupStudentDetachedCriteria create_groupsCriteria() {
		return new GroupStudentDetachedCriteria(createCriteria("ORM__groups"));
	}
	
	public NotificationDetachedCriteria create_notificationsCriteria() {
		return new NotificationDetachedCriteria(createCriteria("ORM__notifications"));
	}
	
	public Student uniqueStudent(PersistentSession session) {
		return (Student) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Student[] listStudent(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Student[]) list.toArray(new Student[list.size()]);
	}
}

