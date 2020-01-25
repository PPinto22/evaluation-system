package wrapper;

import model.Class;

public class ClassTeacherWrapper extends ClassWrapper {
    private UserWrapper teacher;

    public ClassTeacherWrapper(){}

    public ClassTeacherWrapper(Class cl) {
        super(cl);
        this.teacher = new UserWrapper(cl.get_teacher());
    }

    public UserWrapper getTeacher() {
        return teacher;
    }

    public void setTeacher(UserWrapper teacher) {
        this.teacher = teacher;
    }
}
