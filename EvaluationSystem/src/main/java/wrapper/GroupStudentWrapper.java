package wrapper;

import model.persistent.GroupStudent;
import model.persistent.Student;

public class GroupStudentWrapper {

    private boolean accepted;
    private UserWrapper user;

    public GroupStudentWrapper(GroupStudent groupStudent) {
        Student student = groupStudent.get_student();
        this.accepted = groupStudent.isAccepted();
        this.user = new UserWrapper(student);
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public UserWrapper getUser() {
        return user;
    }

    public void setUser(UserWrapper user) {
        this.user = user;
    }
}
