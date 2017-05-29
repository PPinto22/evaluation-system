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

import model.GroupStudent;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class GroupStudentDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression _studentId;
	public final AssociationExpression _student;
	public final IntegerExpression _groupId;
	public final AssociationExpression _group;
	public final BooleanExpression accepted;
	
	public GroupStudentDetachedCriteria() {
		super(GroupStudent.class, GroupStudentCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_studentId = new IntegerExpression("_student.ID", this.getDetachedCriteria());
		_student = new AssociationExpression("_student", this.getDetachedCriteria());
		_groupId = new IntegerExpression("_group.ID", this.getDetachedCriteria());
		_group = new AssociationExpression("_group", this.getDetachedCriteria());
		accepted = new BooleanExpression("accepted", this.getDetachedCriteria());
	}
	
	public GroupStudentDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, GroupStudentCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_studentId = new IntegerExpression("_student.ID", this.getDetachedCriteria());
		_student = new AssociationExpression("_student", this.getDetachedCriteria());
		_groupId = new IntegerExpression("_group.ID", this.getDetachedCriteria());
		_group = new AssociationExpression("_group", this.getDetachedCriteria());
		accepted = new BooleanExpression("accepted", this.getDetachedCriteria());
	}
	
	public StudentDetachedCriteria create_studentCriteria() {
		return new StudentDetachedCriteria(createCriteria("_student"));
	}
	
	public GroupDetachedCriteria create_groupCriteria() {
		return new GroupDetachedCriteria(createCriteria("_group"));
	}
	
	public GroupStudent uniqueGroupStudent(PersistentSession session) {
		return (GroupStudent) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public GroupStudent[] listGroupStudent(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (GroupStudent[]) list.toArray(new GroupStudent[list.size()]);
	}
}

