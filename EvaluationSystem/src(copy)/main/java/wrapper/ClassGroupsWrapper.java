package wrapper;

import model.persistent.Class;
import model.persistent.Group;

import java.util.ArrayList;
import java.util.List;

public class ClassGroupsWrapper extends ClassWrapper {

    private List<GroupWrapper> groups;

    public ClassGroupsWrapper(){}

    public ClassGroupsWrapper(Class cl) {
        super(cl);
        this.groups = new ArrayList<>();
        for(Group group: cl._groups.toArray()){
            this.groups.add(new GroupWrapper(group));
        }
    }

    public List<GroupWrapper> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupWrapper> groups) {
        this.groups = groups;
    }
}
