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
import model.persistent.QuestionSubmission;
import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class QuestionSubmissionCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression _answerId;
	public final AssociationExpression _answer;
	public final IntegerExpression _submissionId;
	public final AssociationExpression _submission;
	public final BooleanExpression correct;
	public final IntegerExpression _questionId;
	public final AssociationExpression _question;
	
	public QuestionSubmissionCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		_answerId = new IntegerExpression("_answer.ID", this);
		_answer = new AssociationExpression("_answer", this);
		_submissionId = new IntegerExpression("_submission.ID", this);
		_submission = new AssociationExpression("_submission", this);
		correct = new BooleanExpression("correct", this);
		_questionId = new IntegerExpression("_question.ID", this);
		_question = new AssociationExpression("_question", this);
	}
	
	public QuestionSubmissionCriteria(PersistentSession session) {
		this(session.createCriteria(QuestionSubmission.class));
	}
	
	public QuestionSubmissionCriteria() throws PersistentException {
		this(ClassesPersistentManager.instance().getSession());
	}
	
	public AnswerCriteria create_answerCriteria() {
		return new AnswerCriteria(createCriteria("_answer"));
	}
	
	public SubmissionCriteria create_submissionCriteria() {
		return new SubmissionCriteria(createCriteria("_submission"));
	}
	
	public QuestionScoreCriteria create_questionCriteria() {
		return new QuestionScoreCriteria(createCriteria("_question"));
	}
	
	public QuestionSubmission uniqueQuestionSubmission() {
		return (QuestionSubmission) super.uniqueResult();
	}
	
	public QuestionSubmission[] listQuestionSubmission() {
		java.util.List list = super.list();
		return (QuestionSubmission[]) list.toArray(new QuestionSubmission[list.size()]);
	}
}

