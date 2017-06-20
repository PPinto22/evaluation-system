import exception.ExistentEntityException;
import model.Class;
import model.Teacher;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.ClassService;
import service.TeacherService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class CreateGroupsClassesTeachers {

    @Autowired
    UserService userService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    ClassService classService;

    Teacher teacher;
    List<Class> classes = new ArrayList<>();

    @Test
    public void createGroupsClassesTeachersIfNotExists() throws Exception{
        this.teacher = new Teacher();
        teacher.setEmail("teacher@teacher");
        teacher.setFirstName("Jose");
        teacher.setLastName("Silva");
        teacher.setPassword("password");

        Class cl1 = new Class();
        cl1.setName("class1");
        cl1.setAbbreviation("CL1");
        classes.add(cl1);

        Class cl2 = new Class();
        cl2.setName("class2");
        cl2.setAbbreviation("CL2");
        classes.add(cl2);

        try {
            teacherService.addTeacher(teacher);
        } catch (ExistentEntityException e) {
            teacher = teacherService.getTeacherByEmail(teacher.getEmail());
        }

        for(int i = 0; i < this.classes.size(); i++){
            Class cl = this.classes.get(i);
            if(!classService.exists(teacher, cl.getName())) {
                cl.set_teacher(teacher);
                classService.addClass(cl);
            }
            this.classes.remove(i);
            this.classes.add(i, classService.getClassByName(teacher, cl.getName()));
        }
    }

//    @After
//    public void undo() throws Exception{
//        for(Class cl: this.classes)
//            classService.delete(cl);
//        userService.delete(teacher);
//    }
}
