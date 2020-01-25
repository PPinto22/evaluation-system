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
import model.Answer;
import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class AnswerCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression text;
	public final BooleanExpression correct;
	public final IntegerExpression order;
	public final CollectionExpression _questionSubmission;
	
	public AnswerCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		text = new StringExpression("text", this);
		correct = new BooleanExpression("correct", this);
		order = new IntegerExpression("order", this);
		_questionSubmission = new CollectionExpression("ORM__questionSubmission", this);
	}
	
	public AnswerCriteria(PersistentSession session) {
		this(session.createCriteria(Answer.class));
	}
	
	public AnswerCriteria() throws PersistentException {
		this(ClassesPersistentManager.instance().getSession());
	}
	
	public QuestionSubmissionCriteria create_questionSubmissionCriteria() {
		return new QuestionSubmissionCriteria(createCriteria("ORM__questionSubmission"));
	}
	
	public Answer uniqueAnswer() {
		return (Answer) super.uniqueResult();
	}
	
	public Answer[] listAnswer() {
		java.util.List list = super.list();
		return (Answer[]) list.toArray(new Answer[list.size()]);
	}
}

