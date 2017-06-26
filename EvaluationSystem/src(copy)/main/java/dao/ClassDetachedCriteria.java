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

import model.persistent.Class;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class ClassDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression _teacherId;
	public final AssociationExpression _teacher;
	public final StringExpression name;
	public final StringExpression abbreviation;
	public final CollectionExpression _question;
	public final CollectionExpression _groups;
	
	public ClassDetachedCriteria() {
		super(Class.class, ClassCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_teacherId = new IntegerExpression("_teacher.ID", this.getDetachedCriteria());
		_teacher = new AssociationExpression("_teacher", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		abbreviation = new StringExpression("abbreviation", this.getDetachedCriteria());
		_question = new CollectionExpression("ORM__question", this.getDetachedCriteria());
		_groups = new CollectionExpression("ORM__groups", this.getDetachedCriteria());
	}
	
	public ClassDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, ClassCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_teacherId = new IntegerExpression("_teacher.ID", this.getDetachedCriteria());
		_teacher = new AssociationExpression("_teacher", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		abbreviation = new StringExpression("abbreviation", this.getDetachedCriteria());
		_question = new CollectionExpression("ORM__question", this.getDetachedCriteria());
		_groups = new CollectionExpression("ORM__groups", this.getDetachedCriteria());
	}
	
	public TeacherDetachedCriteria create_teacherCriteria() {
		return new TeacherDetachedCriteria(createCriteria("_teacher"));
	}
	
	public QuestionDetachedCriteria create_questionCriteria() {
		return new QuestionDetachedCriteria(createCriteria("ORM__question"));
	}
	
	public GroupDetachedCriteria create_groupsCriteria() {
		return new GroupDetachedCriteria(createCriteria("ORM__groups"));
	}
	
	public Class uniqueClass(PersistentSession session) {
		return (Class) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Class[] listClass(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Class[]) list.toArray(new Class[list.size()]);
	}
}

