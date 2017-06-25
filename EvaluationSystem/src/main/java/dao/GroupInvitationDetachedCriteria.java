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

import model.persistent.GroupInvitation;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class GroupInvitationDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression _userId;
	public final AssociationExpression _user;
	public final IntegerExpression _groupId;
	public final AssociationExpression _group;
	
	public GroupInvitationDetachedCriteria() {
		super(GroupInvitation.class, GroupInvitationCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_userId = new IntegerExpression("_user.ID", this.getDetachedCriteria());
		_user = new AssociationExpression("_user", this.getDetachedCriteria());
		_groupId = new IntegerExpression("_group.ID", this.getDetachedCriteria());
		_group = new AssociationExpression("_group", this.getDetachedCriteria());
	}
	
	public GroupInvitationDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, GroupInvitationCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		_userId = new IntegerExpression("_user.ID", this.getDetachedCriteria());
		_user = new AssociationExpression("_user", this.getDetachedCriteria());
		_groupId = new IntegerExpression("_group.ID", this.getDetachedCriteria());
		_group = new AssociationExpression("_group", this.getDetachedCriteria());
	}
	
	public GroupDetachedCriteria create_groupCriteria() {
		return new GroupDetachedCriteria(createCriteria("_group"));
	}
	
	public UserDetachedCriteria create_userCriteria() {
		return new UserDetachedCriteria(createCriteria("_user"));
	}
	
	public GroupInvitation uniqueGroupInvitation(PersistentSession session) {
		return (GroupInvitation) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public GroupInvitation[] listGroupInvitation(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (GroupInvitation[]) list.toArray(new GroupInvitation[list.size()]);
	}
}

