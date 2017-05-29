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

import model.QuestionScore;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class QuestionScoreDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression _questionId;
	public final AssociationExpression _question;
	public final FloatExpression score;
	
	public QuestionScoreDetachedCriteria() {
		super(QuestionScore.class, QuestionScoreCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_questionId = new IntegerExpression("_question.ID", this.getDetachedCriteria());
		_question = new AssociationExpression("_question", this.getDetachedCriteria());
		score = new FloatExpression("score", this.getDetachedCriteria());
	}
	
	public QuestionScoreDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, QuestionScoreCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_questionId = new IntegerExpression("_question.ID", this.getDetachedCriteria());
		_question = new AssociationExpression("_question", this.getDetachedCriteria());
		score = new FloatExpression("score", this.getDetachedCriteria());
	}
	
	public QuestionDetachedCriteria create_questionCriteria() {
		return new QuestionDetachedCriteria(createCriteria("_question"));
	}
	
	public QuestionScore uniqueQuestionScore(PersistentSession session) {
		return (QuestionScore) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public QuestionScore[] listQuestionScore(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (QuestionScore[]) list.toArray(new QuestionScore[list.size()]);
	}
}

