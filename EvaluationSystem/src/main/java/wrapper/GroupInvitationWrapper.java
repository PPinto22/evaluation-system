package wrapper;

import model.GroupInvitation;

public class GroupInvitationWrapper implements NotificationWrapper {

    private int ID;
    private String type;
    private GroupWrapper group;

    public GroupInvitationWrapper(){
    }

    public GroupInvitationWrapper(GroupInvitation invitation){
        this.group = new GroupWrapper(invitation.get_group());
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String getType() {
        return "group invitation";
    }

    public GroupWrapper getGroup() {
        return group;
    }

    public void setGroup(GroupWrapper group) {
        this.group = group;
    }
}
