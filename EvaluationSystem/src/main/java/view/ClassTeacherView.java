package view;

import model.Class;

public class ClassTeacherView extends ClassView {
    private UserView teacher;

    public ClassTeacherView(){}

    public ClassTeacherView(Class cl) {
        super(cl);
        this.teacher = new UserView(cl.get_teacher());
    }

    public UserView getTeacher() {
        return teacher;
    }

    public void setTeacher(UserView teacher) {
        this.teacher = teacher;
    }
}
