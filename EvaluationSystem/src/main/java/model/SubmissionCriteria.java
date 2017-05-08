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
import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class SubmissionCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression _examId;
	public final AssociationExpression _exam;
	public final IntegerExpression _studentId;
	public final AssociationExpression _student;
	public final FloatExpression score;
	public final CollectionExpression _questionSubmissions;
	
	public SubmissionCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		_examId = new IntegerExpression("_exam.ID", this);
		_exam = new AssociationExpression("_exam", this);
		_studentId = new IntegerExpression("_student.ID", this);
		_student = new AssociationExpression("_student", this);
		score = new FloatExpression("score", this);
		_questionSubmissions = new CollectionExpression("ORM__questionSubmissions", this);
	}
	
	public SubmissionCriteria(PersistentSession session) {
		this(session.createCriteria(Submission.class));
	}
	
	public SubmissionCriteria() throws PersistentException {
		this(ClassesPersistentManager.instance().getSession());
	}
	
	public ExamCriteria create_examCriteria() {
		return new ExamCriteria(createCriteria("_exam"));
	}
	
	public StudentCriteria create_studentCriteria() {
		return new StudentCriteria(createCriteria("_student"));
	}
	
	public QuestionSubmissionCriteria create_questionSubmissionsCriteria() {
		return new QuestionSubmissionCriteria(createCriteria("ORM__questionSubmissions"));
	}
	
	public Submission uniqueSubmission() {
		return (Submission) super.uniqueResult();
	}
	
	public Submission[] listSubmission() {
		java.util.List list = super.list();
		return (Submission[]) list.toArray(new Submission[list.size()]);
	}
}

