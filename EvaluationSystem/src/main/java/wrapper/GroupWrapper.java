package wrapper;

import model.persistent.Group;

public class GroupWrapper {

    private int ID;
    private String name;

    public GroupWrapper() {

    }

    public GroupWrapper(Group group) {
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
