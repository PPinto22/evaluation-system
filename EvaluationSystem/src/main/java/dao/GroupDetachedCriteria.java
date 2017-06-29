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

import model.Group;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class GroupDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression _classId;
	public final AssociationExpression _class;
	public final StringExpression name;
	public final CollectionExpression _students;
	public final CollectionExpression _exams;
	
	public GroupDetachedCriteria() {
		super(Group.class, GroupCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_classId = new IntegerExpression("_class.ID", this.getDetachedCriteria());
		_class = new AssociationExpression("_class", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		_students = new CollectionExpression("ORM__students", this.getDetachedCriteria());
		_exams = new CollectionExpression("ORM__exams", this.getDetachedCriteria());
	}
	
	public GroupDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, GroupCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_classId = new IntegerExpression("_class.ID", this.getDetachedCriteria());
		_class = new AssociationExpression("_class", this.getDetachedCriteria());
		name = new StringExpression("name", this.getDetachedCriteria());
		_students = new CollectionExpression("ORM__students", this.getDetachedCriteria());
		_exams = new CollectionExpression("ORM__exams", this.getDetachedCriteria());
	}
	
	public ClassDetachedCriteria create_classCriteria() {
		return new ClassDetachedCriteria(createCriteria("_class"));
	}
	
	public GroupStudentDetachedCriteria create_studentsCriteria() {
		return new GroupStudentDetachedCriteria(createCriteria("ORM__students"));
	}
	
	public ExamDetachedCriteria create_examsCriteria() {
		return new ExamDetachedCriteria(createCriteria("ORM__exams"));
	}
	
	public Group uniqueGroup(PersistentSession session) {
		return (Group) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Group[] listGroup(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Group[]) list.toArray(new Group[list.size()]);
	}
}

