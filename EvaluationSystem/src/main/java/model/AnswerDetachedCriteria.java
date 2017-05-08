package model; /**
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
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class AnswerDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final StringExpression text;
	public final BooleanExpression correct;
	public final CollectionExpression _questionSubmission;
	
	public AnswerDetachedCriteria() {
		super(Answer.class, AnswerCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		text = new StringExpression("text", this.getDetachedCriteria());
		correct = new BooleanExpression("correct", this.getDetachedCriteria());
		_questionSubmission = new CollectionExpression("ORM__questionSubmission", this.getDetachedCriteria());
	}
	
	public AnswerDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, AnswerCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		text = new StringExpression("text", this.getDetachedCriteria());
		correct = new BooleanExpression("correct", this.getDetachedCriteria());
		_questionSubmission = new CollectionExpression("ORM__questionSubmission", this.getDetachedCriteria());
	}
	
	public QuestionSubmissionDetachedCriteria create_questionSubmissionCriteria() {
		return new QuestionSubmissionDetachedCriteria(createCriteria("ORM__questionSubmission"));
	}
	
	public Answer uniqueAnswer(PersistentSession session) {
		return (Answer) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Answer[] listAnswer(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Answer[]) list.toArray(new Answer[list.size()]);
	}
}

