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

public class SubmissionDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression _examId;
	public final AssociationExpression _exam;
	public final IntegerExpression _studentId;
	public final AssociationExpression _student;
	public final FloatExpression score;
	public final CollectionExpression _questionSubmissions;
	
	public SubmissionDetachedCriteria() {
		super(Submission.class, SubmissionCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_examId = new IntegerExpression("_exam.ID", this.getDetachedCriteria());
		_exam = new AssociationExpression("_exam", this.getDetachedCriteria());
		_studentId = new IntegerExpression("_student.ID", this.getDetachedCriteria());
		_student = new AssociationExpression("_student", this.getDetachedCriteria());
		score = new FloatExpression("score", this.getDetachedCriteria());
		_questionSubmissions = new CollectionExpression("ORM__questionSubmissions", this.getDetachedCriteria());
	}
	
	public SubmissionDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, SubmissionCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_examId = new IntegerExpression("_exam.ID", this.getDetachedCriteria());
		_exam = new AssociationExpression("_exam", this.getDetachedCriteria());
		_studentId = new IntegerExpression("_student.ID", this.getDetachedCriteria());
		_student = new AssociationExpression("_student", this.getDetachedCriteria());
		score = new FloatExpression("score", this.getDetachedCriteria());
		_questionSubmissions = new CollectionExpression("ORM__questionSubmissions", this.getDetachedCriteria());
	}
	
	public ExamDetachedCriteria create_examCriteria() {
		return new ExamDetachedCriteria(createCriteria("_exam"));
	}
	
	public StudentDetachedCriteria create_studentCriteria() {
		return new StudentDetachedCriteria(createCriteria("_student"));
	}
	
	public QuestionSubmissionDetachedCriteria create_questionSubmissionsCriteria() {
		return new QuestionSubmissionDetachedCriteria(createCriteria("ORM__questionSubmissions"));
	}
	
	public Submission uniqueSubmission(PersistentSession session) {
		return (Submission) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Submission[] listSubmission(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Submission[]) list.toArray(new Submission[list.size()]);
	}
}

