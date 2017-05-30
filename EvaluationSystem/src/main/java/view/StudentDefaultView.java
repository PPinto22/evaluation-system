package view;

import model.GroupStudent;
import model.Student;
import model.Submission;

import java.util.ArrayList;
import java.util.List;

public class StudentDefaultView extends UserNotificationsView{
    private List<GroupView> groups;
    private List<Integer> submissionIDs;

    public StudentDefaultView(){}

    public StudentDefaultView(Student student){
        super(student);
        this.groups = new ArrayList<>();
        for(GroupStudent groupStudent: student._groups.toArray()){
            if(groupStudent.getAccepted()){
                this.groups.add(new GroupClassView(groupStudent.get_group()));
            }
        }
        this.submissionIDs = new ArrayList<>();
        for(Submission submission: student._submissions.toArray()){
            this.submissionIDs.add(submission.getID());
        }
    }

    public List<GroupView> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupView> groups) {
        this.groups = groups;
    }

    public List<Integer> getSubmissionIDs() {
        return submissionIDs;
    }

    public void setSubmissionIDs(List<Integer> submissionIDs) {
        this.submissionIDs = submissionIDs;
    }
}
