/**
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

public class ClassCriteria extends AbstractORMCriteria {
	public final IntegerExpression attribute;
	public final IntegerExpression ID;
	public final StringExpression name;
	public final StringExpression abbreviation;
	public final IntegerExpression _teacherId;
	public final AssociationExpression _teacher;
	public final CollectionExpression _question;
	
	public ClassCriteria(Criteria criteria) {
		super(criteria);
		attribute = new IntegerExpression("attribute", this);
		ID = new IntegerExpression("ID", this);
		name = new StringExpression("name", this);
		abbreviation = new StringExpression("abbreviation", this);
		_teacherId = new IntegerExpression("_teacher.attribute", this);
		_teacher = new AssociationExpression("_teacher", this);
		_question = new CollectionExpression("ORM__question", this);
	}
	
	public ClassCriteria(PersistentSession session) {
		this(session.createCriteria(Class.class));
	}
	
	public ClassCriteria() throws PersistentException {
		this(ClassesPersistentManager.instance().getSession());
	}
	
	public TeacherCriteria create_teacherCriteria() {
		return new TeacherCriteria(createCriteria("_teacher"));
	}
	
	public QuestionCriteria create_questionCriteria() {
		return new QuestionCriteria(createCriteria("ORM__question"));
	}
	
	public Class uniqueClass() {
		return (Class) super.uniqueResult();
	}
	
	public Class[] listClass() {
		java.util.List list = super.list();
		return (Class[]) list.toArray(new Class[list.size()]);
	}
}

