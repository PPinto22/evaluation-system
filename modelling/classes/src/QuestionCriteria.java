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

public class QuestionCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression text;
	public final StringExpression category;
	public final IntegerExpression dificulty;
	public final IntegerExpression _classId;
	public final AssociationExpression _class;
	
	public QuestionCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		text = new StringExpression("text", this);
		category = new StringExpression("category", this);
		dificulty = new IntegerExpression("dificulty", this);
		_classId = new IntegerExpression("_class.attribute", this);
		_class = new AssociationExpression("_class", this);
	}
	
	public QuestionCriteria(PersistentSession session) {
		this(session.createCriteria(Question.class));
	}
	
	public QuestionCriteria() throws PersistentException {
		this(ClassesPersistentManager.instance().getSession());
	}
	
	public ClassCriteria create_classCriteria() {
		return new ClassCriteria(createCriteria("_class"));
	}
	
	public Question uniqueQuestion() {
		return (Question) super.uniqueResult();
	}
	
	public Question[] listQuestion() {
		java.util.List list = super.list();
		return (Question[]) list.toArray(new Question[list.size()]);
	}
}

