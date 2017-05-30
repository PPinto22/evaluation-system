package view;

import model.Group;

public class GroupView {

    private int ID;
    private String name;

    public GroupView() {

    }

    public GroupView(Group group) {
        this.setID(group.getID());
        this.setName(group.getName());
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
