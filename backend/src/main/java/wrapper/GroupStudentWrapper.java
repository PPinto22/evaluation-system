package wrapper;

import model.GroupStudent;
import model.Student;

public class GroupStudentWrapper implements Comparable<GroupStudentWrapper>{

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

    @Override
    public int compareTo(GroupStudentWrapper o) {
        if (this.user.getId() > o.user.getId())
            return 1;
        else if (this.user.getId() < o.user.getId())
            return -1;
        else
            return 0;
    }
}
