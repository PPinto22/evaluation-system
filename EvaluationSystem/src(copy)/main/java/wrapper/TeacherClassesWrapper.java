package wrapper;

import model.persistent.Teacher;

import java.util.ArrayList;
import java.util.List;
import model.persistent.Class;

public class TeacherClassesWrapper extends UserWrapper {

    private List<ClassWrapper> classes;

    public TeacherClassesWrapper(){}

    public TeacherClassesWrapper(Teacher teacher){
        super(teacher);
        this.classes = new ArrayList<>();
        for(Class cl: teacher._classes.toArray()){
            this.classes.add(new ClassGroupsWrapper(cl));
        }
    }
}
