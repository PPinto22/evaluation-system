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

public class ExamCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression _groupId;
	public final AssociationExpression _group;
	public final DateExpression beginDate;
	public final DateExpression duration;
	public final CollectionExpression _submissions;
	public final CollectionExpression _questions;
	
	public ExamCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		_groupId = new IntegerExpression("_group.ID", this);
		_group = new AssociationExpression("_group", this);
		beginDate = new DateExpression("beginDate", this);
		duration = new DateExpression("duration", this);
		_submissions = new CollectionExpression("ORM__submissions", this);
		_questions = new CollectionExpression("ORM__questions", this);
	}
	
	public ExamCriteria(PersistentSession session) {
		this(session.createCriteria(Exam.class));
	}
	
	public ExamCriteria() throws PersistentException {
		this(ClassesPersistentManager.instance().getSession());
	}
	
	public GroupCriteria create_groupCriteria() {
		return new GroupCriteria(createCriteria("_group"));
	}
	
	public SubmissionCriteria create_submissionsCriteria() {
		return new SubmissionCriteria(createCriteria("ORM__submissions"));
	}
	
	public QuestionScoreCriteria create_questionsCriteria() {
		return new QuestionScoreCriteria(createCriteria("ORM__questions"));
	}
	
	public Exam uniqueExam() {
		return (Exam) super.uniqueResult();
	}
	
	public Exam[] listExam() {
		java.util.List list = super.list();
		return (Exam[]) list.toArray(new Exam[list.size()]);
	}
}

