package wrapper;

import model.GroupInvitation;

public class GroupInvitationWrapper implements NotificationWrapper {

    private int ID;
    private String type;
    private GroupClassWrapper group;

    public GroupInvitationWrapper(){
    }

    public GroupInvitationWrapper(GroupInvitation invitation){
        this.group = new GroupClassWrapper(invitation.get_group());
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
