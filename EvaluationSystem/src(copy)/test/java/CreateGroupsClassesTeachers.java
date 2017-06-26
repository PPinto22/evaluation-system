import exception.ExistentEntityException;
import model.persistent.Class;
import model.persistent.Group;
import model.persistent.Teacher;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.ClassService;
import service.GroupService;
import service.TeacherService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class CreateGroupsClassesTeachers {

    private final static boolean UNDO = true;

    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ClassService classService;
    @Autowired
    private GroupService groupService;

    private Teacher teacher;
    private List<Class> classes = new ArrayList<>();
    private List<Group> groups = new ArrayList<>();

    @Test
    public void createGroupsClassesTeachersIfNotExists() throws Exception{
        this.teacher = new Teacher();
        teacher.setEmail("teacher@teacher");
        teacher.setFirstName("Jose");
        teacher.setLastName("Silva");
        teacher.setPassword("password");

        for(int i = 1; i<=2; i++){
            Class cl = new Class();
            cl.setName("class"+i);
            cl.setAbbreviation("CL"+2);
            this.classes.add(cl);
        }

        for(int i = 1; i<=4; i++) {
            Group group = new Group();
            group.setName("group"+i);
            this.groups.add(group);
        }

        try {
            teacher = teacherService.addTeacher(teacher, true);
        } catch (ExistentEntityException e) {
            teacher = teacherService.getTeacherByEmail(teacher.getEmail());
        }


        for(int i = 0; i < this.classes.size(); i++){
            Class cl = this.classes.get(i);
            if(!classService.exists(teacher, cl.getName())) {
                teacherService.addClassToTeacher(teacher, cl);
            }
            this.classes.remove(i);
            this.classes.add(i, classService.getClassByName(teacher, cl.getName()));
        }

        for(int i = 0; i<this.groups.size(); i++){
            Group group = this.groups.get(i);
            Class cl = this.classes.get( (i+this.groups.size()) %this.classes.size());
            if(!this.groupService.exists(cl,group.getName())){
                this.classService.addGroupToClass(cl, group);
            }
            this.groups.remove(i);
            this.groups.add(i, groupService.getGroupByName(cl, group.getName()));
        }
    }

    @After
    public void undo() throws Exception{
        for(Group group: this.groups)
            groupService.delete(group);
        for (Class cl : this.classes)
            classService.delete(cl);
        userService.delete(teacher);
    }
}
