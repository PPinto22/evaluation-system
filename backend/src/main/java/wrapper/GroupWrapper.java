package wrapper;

import model.Group;

public class GroupWrapper implements Comparable<GroupWrapper>{

    private int id;
    private String name;

    public GroupWrapper() {

    }

    public GroupWrapper(Group group) {
        this.setId(group.getID());
        this.setName(group.getName());
    }

    @Override
    public int compareTo(GroupWrapper o) {
        return name.toLowerCase().compareTo(o.getName().toLowerCase());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupWrapper that = (GroupWrapper) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
