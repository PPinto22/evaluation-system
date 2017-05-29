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
import model.Group;
import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class GroupCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression _classId;
	public final AssociationExpression _class;
	public final StringExpression name;
	public final CollectionExpression _students;
	public final CollectionExpression _exams;
	
	public GroupCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		_classId = new IntegerExpression("_class.ID", this);
		_class = new AssociationExpression("_class", this);
		name = new StringExpression("name", this);
		_students = new CollectionExpression("ORM__students", this);
		_exams = new CollectionExpression("ORM__exams", this);
	}
	
	public GroupCriteria(PersistentSession session) {
		this(session.createCriteria(Group.class));
	}
	
	public GroupCriteria() throws PersistentException {
		this(ClassesPersistentManager.instance().getSession());
	}
	
	public ClassCriteria create_classCriteria() {
		return new ClassCriteria(createCriteria("_class"));
	}
	
	public GroupStudentCriteria create_studentsCriteria() {
		return new GroupStudentCriteria(createCriteria("ORM__students"));
	}
	
	public ExamCriteria create_examsCriteria() {
		return new ExamCriteria(createCriteria("ORM__exams"));
	}
	
	public Group uniqueGroup() {
		return (Group) super.uniqueResult();
	}
	
	public Group[] listGroup() {
		java.util.List list = super.list();
		return (Group[]) list.toArray(new Group[list.size()]);
	}
}

