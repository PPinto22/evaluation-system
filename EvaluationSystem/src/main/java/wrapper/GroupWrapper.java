package wrapper;

import model.persistent.Group;

public class GroupWrapper {

    private int id;
    private String name;

    public GroupWrapper() {

    }

    public GroupWrapper(Group group) {
        this.setId(group.getID());
        this.setName(group.getName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
