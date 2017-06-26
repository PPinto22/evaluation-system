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
import model.persistent.QuestionScore;
import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class QuestionScoreCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression _questionId;
	public final AssociationExpression _question;
	public final IntegerExpression _examId;
	public final AssociationExpression _exam;
	public final FloatExpression score;
	public final IntegerExpression order;
	
	public QuestionScoreCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		_questionId = new IntegerExpression("_question.ID", this);
		_question = new AssociationExpression("_question", this);
		_examId = new IntegerExpression("_exam.ID", this);
		_exam = new AssociationExpression("_exam", this);
		score = new FloatExpression("score", this);
		order = new IntegerExpression("order", this);
	}
	
	public QuestionScoreCriteria(PersistentSession session) {
		this(session.createCriteria(QuestionScore.class));
	}
	
	public QuestionScoreCriteria() throws PersistentException {
		this(ClassesPersistentManager.instance().getSession());
	}
	
	public QuestionCriteria create_questionCriteria() {
		return new QuestionCriteria(createCriteria("_question"));
	}
	
	public ExamCriteria create_examCriteria() {
		return new ExamCriteria(createCriteria("_exam"));
	}
	
	public QuestionScore uniqueQuestionScore() {
		return (QuestionScore) super.uniqueResult();
	}
	
	public QuestionScore[] listQuestionScore() {
		java.util.List list = super.list();
		return (QuestionScore[]) list.toArray(new QuestionScore[list.size()]);
	}
}

