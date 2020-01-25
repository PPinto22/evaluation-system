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
import model.GroupStudent;
import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class GroupStudentCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression _studentId;
	public final AssociationExpression _student;
	public final IntegerExpression _groupId;
	public final AssociationExpression _group;
	public final BooleanExpression accepted;
	
	public GroupStudentCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		_studentId = new IntegerExpression("_student.ID", this);
		_student = new AssociationExpression("_student", this);
		_groupId = new IntegerExpression("_group.ID", this);
		_group = new AssociationExpression("_group", this);
		accepted = new BooleanExpression("accepted", this);
	}
	
	public GroupStudentCriteria(PersistentSession session) {
		this(session.createCriteria(GroupStudent.class));
	}
	
	public GroupStudentCriteria() throws PersistentException {
		this(ClassesPersistentManager.instance().getSession());
	}
	
	public StudentCriteria create_studentCriteria() {
		return new StudentCriteria(createCriteria("_student"));
	}
	
	public GroupCriteria create_groupCriteria() {
		return new GroupCriteria(createCriteria("_group"));
	}
	
	public GroupStudent uniqueGroupStudent() {
		return (GroupStudent) super.uniqueResult();
	}
	
	public GroupStudent[] listGroupStudent() {
		java.util.List list = super.list();
		return (GroupStudent[]) list.toArray(new GroupStudent[list.size()]);
	}
}

