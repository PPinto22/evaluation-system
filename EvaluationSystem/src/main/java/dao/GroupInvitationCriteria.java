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
import model.GroupInvitation;
import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class GroupInvitationCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression _userId;
	public final AssociationExpression _user;
	public final IntegerExpression _groupId;
	public final AssociationExpression _group;
	
	public GroupInvitationCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		_userId = new IntegerExpression("_user.ID", this);
		_user = new AssociationExpression("_user", this);
		_groupId = new IntegerExpression("_group.ID", this);
		_group = new AssociationExpression("_group", this);
	}
	
	public GroupInvitationCriteria(PersistentSession session) {
		this(session.createCriteria(GroupInvitation.class));
	}
	
	public GroupInvitationCriteria() throws PersistentException {
		this(ClassesPersistentManager.instance().getSession());
	}
	
	public GroupCriteria create_groupCriteria() {
		return new GroupCriteria(createCriteria("_group"));
	}
	
	public UserCriteria create_userCriteria() {
		return new UserCriteria(createCriteria("_user"));
	}
	
	public GroupInvitation uniqueGroupInvitation() {
		return (GroupInvitation) super.uniqueResult();
	}
	
	public GroupInvitation[] listGroupInvitation() {
		java.util.List list = super.list();
		return (GroupInvitation[]) list.toArray(new GroupInvitation[list.size()]);
	}
}

