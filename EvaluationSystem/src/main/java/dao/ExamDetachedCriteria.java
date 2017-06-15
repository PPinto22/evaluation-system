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

import model.Exam;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class ExamDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression _groupId;
	public final AssociationExpression _group;
	public final TimestampExpression beginDate;
	public final TimeExpression duration;
	public final CollectionExpression _submissions;
	public final CollectionExpression _questions;
	
	public ExamDetachedCriteria() {
		super(Exam.class, ExamCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_groupId = new IntegerExpression("_group.ID", this.getDetachedCriteria());
		_group = new AssociationExpression("_group", this.getDetachedCriteria());
		beginDate = new TimestampExpression("beginDate", this.getDetachedCriteria());
		duration = new TimeExpression("duration", this.getDetachedCriteria());
		_submissions = new CollectionExpression("ORM__submissions", this.getDetachedCriteria());
		_questions = new CollectionExpression("ORM__questions", this.getDetachedCriteria());
	}
	
	public ExamDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, ExamCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_groupId = new IntegerExpression("_group.ID", this.getDetachedCriteria());
		_group = new AssociationExpression("_group", this.getDetachedCriteria());
		beginDate = new TimestampExpression("beginDate", this.getDetachedCriteria());
		duration = new TimeExpression("duration", this.getDetachedCriteria());
		_submissions = new CollectionExpression("ORM__submissions", this.getDetachedCriteria());
		_questions = new CollectionExpression("ORM__questions", this.getDetachedCriteria());
	}
	
	public GroupDetachedCriteria create_groupCriteria() {
		return new GroupDetachedCriteria(createCriteria("_group"));
	}
	
	public SubmissionDetachedCriteria create_submissionsCriteria() {
		return new SubmissionDetachedCriteria(createCriteria("ORM__submissions"));
	}
	
	public QuestionScoreDetachedCriteria create_questionsCriteria() {
		return new QuestionScoreDetachedCriteria(createCriteria("ORM__questions"));
	}
	
	public Exam uniqueExam(PersistentSession session) {
		return (Exam) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Exam[] listExam(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Exam[]) list.toArray(new Exam[list.size()]);
	}
}

