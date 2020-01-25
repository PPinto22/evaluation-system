package wrapper;

import model.GroupInvitation;

public class GroupInvitationWrapper implements NotificationWrapper {

    private int id;
    private String type;
    private GroupClassWrapper group;

    public GroupInvitationWrapper(){
    }

    public GroupInvitationWrapper(GroupInvitation invitation){
        this.id = invitation.getID();
        this.group = new GroupClassWrapper(invitation.get_group());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getType() {
        return "Group invitation";
    }

    public GroupClassWrapper getGroup() {
        return group;
    }

    public void setGroup(GroupClassWrapper group) {
        this.group = group;
    }
}
