package wrapper;

import model.persistent.GroupStudent;
import model.persistent.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentGroupsWrapper extends UserNotificationsWrapper {
    private List<GroupWrapper> groups;

    public StudentGroupsWrapper(){}

    public StudentGroupsWrapper(Student student){
        super(student);
        this.groups = new ArrayList<>();
        for(GroupStudent groupStudent: student._groups.toArray()){
            if(groupStudent.isAccepted()){
                this.groups.add(new GroupClassWrapper(groupStudent.get_group()));
            }
        }
    }

    public List<GroupWrapper> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupWrapper> groups) {
        this.groups = groups;
    }
}
