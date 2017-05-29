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

import model.QuestionSubmission;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class QuestionSubmissionDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression _answerId;
	public final AssociationExpression _answer;
	public final IntegerExpression _questionId;
	public final AssociationExpression _question;
	public final IntegerExpression _submissionId;
	public final AssociationExpression _submission;
	public final BooleanExpression correct;
	
	public QuestionSubmissionDetachedCriteria() {
		super(QuestionSubmission.class, QuestionSubmissionCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_answerId = new IntegerExpression("_answer.ID", this.getDetachedCriteria());
		_answer = new AssociationExpression("_answer", this.getDetachedCriteria());
		_questionId = new IntegerExpression("_question.ID", this.getDetachedCriteria());
		_question = new AssociationExpression("_question", this.getDetachedCriteria());
		_submissionId = new IntegerExpression("_submission.ID", this.getDetachedCriteria());
		_submission = new AssociationExpression("_submission", this.getDetachedCriteria());
		correct = new BooleanExpression("correct", this.getDetachedCriteria());
	}
	
	public QuestionSubmissionDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, QuestionSubmissionCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_answerId = new IntegerExpression("_answer.ID", this.getDetachedCriteria());
		_answer = new AssociationExpression("_answer", this.getDetachedCriteria());
		_questionId = new IntegerExpression("_question.ID", this.getDetachedCriteria());
		_question = new AssociationExpression("_question", this.getDetachedCriteria());
		_submissionId = new IntegerExpression("_submission.ID", this.getDetachedCriteria());
		_submission = new AssociationExpression("_submission", this.getDetachedCriteria());
		correct = new BooleanExpression("correct", this.getDetachedCriteria());
	}
	
	public AnswerDetachedCriteria create_answerCriteria() {
		return new AnswerDetachedCriteria(createCriteria("_answer"));
	}
	
	public QuestionDetachedCriteria create_questionCriteria() {
		return new QuestionDetachedCriteria(createCriteria("_question"));
	}
	
	public SubmissionDetachedCriteria create_submissionCriteria() {
		return new SubmissionDetachedCriteria(createCriteria("_submission"));
	}
	
	public QuestionSubmission uniqueQuestionSubmission(PersistentSession session) {
		return (QuestionSubmission) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public QuestionSubmission[] listQuestionSubmission(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (QuestionSubmission[]) list.toArray(new QuestionSubmission[list.size()]);
	}
}

