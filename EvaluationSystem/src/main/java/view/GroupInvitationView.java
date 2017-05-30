package view;

import model.GroupInvitation;

public class GroupInvitationView implements NotificationView{

    private int ID;
    private String type;
    private GroupView group;

    public GroupInvitationView(){
    }

    public GroupInvitationView(GroupInvitation invitation){
        this.group = new GroupView(invitation.get_group());
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

    public GroupView getGroup() {
        return group;
    }

    public void setGroup(GroupView group) {
        this.group = group;
    }
}
