package view;

import model.Teacher;

import java.util.ArrayList;
import java.util.List;
import model.Class;

public class TeacherDefaultView extends UserView {

    private List<ClassView> classes;

    public TeacherDefaultView(){}

    public TeacherDefaultView(Teacher teacher){
        super(teacher);
        this.classes = new ArrayList<>();
        for(Class cl: teacher._classes.toArray()){
            this.classes.add(new ClassGroupsView(cl));
        }
    }
}
