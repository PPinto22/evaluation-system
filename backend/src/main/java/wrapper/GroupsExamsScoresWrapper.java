package wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import model.Exam;
import model.Group;
import model.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupsExamsScoresWrapper{

    private List<GroupExamsWrapper> groups;

    public GroupsExamsScoresWrapper(){}

    public GroupsExamsScoresWrapper(Map<Group, Map<Exam, Score>> scoreMap){
        this.groups = new ArrayList<>();
        for(Group group: scoreMap.keySet()){
            this.groups.add(new GroupExamsWrapper(group, scoreMap.get(group)));
        }
    }

    public List<GroupExamsWrapper> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupExamsWrapper> groups) {
        this.groups = groups;
    }
}
