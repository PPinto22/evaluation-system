package view;

import model.Class;
import model.Group;

import java.util.ArrayList;
import java.util.List;

public class ClassGroupsView extends ClassView {

    private List<GroupView> groups;

    public ClassGroupsView(){}

    public ClassGroupsView(Class cl) {
        super(cl);
        this.groups = new ArrayList<>();
        for(Group group: cl._groups.toArray()){
            this.groups.add(new GroupView(group));
        }
    }

    public List<GroupView> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupView> groups) {
        this.groups = groups;
    }
}
