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

import model.persistent.Question;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class QuestionDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression _classId;
	public final AssociationExpression _class;
	public final StringExpression text;
	public final StringExpression category;
	public final IntegerExpression dificulty;
	public final CollectionExpression _answers;
	
	public QuestionDetachedCriteria() {
		super(Question.class, QuestionCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_classId = new IntegerExpression("_class.ID", this.getDetachedCriteria());
		_class = new AssociationExpression("_class", this.getDetachedCriteria());
		text = new StringExpression("text", this.getDetachedCriteria());
		category = new StringExpression("category", this.getDetachedCriteria());
		dificulty = new IntegerExpression("dificulty", this.getDetachedCriteria());
		_answers = new CollectionExpression("ORM__answers", this.getDetachedCriteria());
	}
	
	public QuestionDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, QuestionCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_classId = new IntegerExpression("_class.ID", this.getDetachedCriteria());
		_class = new AssociationExpression("_class", this.getDetachedCriteria());
		text = new StringExpression("text", this.getDetachedCriteria());
		category = new StringExpression("category", this.getDetachedCriteria());
		dificulty = new IntegerExpression("dificulty", this.getDetachedCriteria());
		_answers = new CollectionExpression("ORM__answers", this.getDetachedCriteria());
	}
	
	public ClassDetachedCriteria create_classCriteria() {
		return new ClassDetachedCriteria(createCriteria("_class"));
	}
	
	public AnswerDetachedCriteria create_answersCriteria() {
		return new AnswerDetachedCriteria(createCriteria("ORM__answers"));
	}
	
	public Question uniqueQuestion(PersistentSession session) {
		return (Question) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Question[] listQuestion(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Question[]) list.toArray(new Question[list.size()]);
	}
}

