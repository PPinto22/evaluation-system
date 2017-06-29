package service;


import dao.TeacherDAO;
import exception.ExistentEntityException;
import exception.InvalidUserTypeException;
import exception.MissingInformationException;
import exception.NonExistentEntityException;
import model.Class;
import model.Group;
import model.Teacher;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService{

    private UserService userService;
    private ClassService classService;
    private TeacherDAO teacherDAO;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setClassService(ClassService classService) {
        this.classService = classService;
    }

    public TeacherServiceImpl(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Teacher addTeacher(Teacher teacher, boolean register) throws MissingInformationException, PersistentException, ExistentEntityException {
        Teacher addedTeacher = null;
        try {
            addedTeacher = (Teacher) userService.signup(teacher, "teacher", register);
        } catch (InvalidUserTypeException e) {
            e.printStackTrace();
        }
        return addedTeacher;
    }

    @Override
    public Teacher getTeacherByID(int ID) throws PersistentException, NonExistentEntityException {
        if(!this.teacherDAO.exists(ID))
            throw new NonExistentEntityException();

        return this.teacherDAO.loadTeacherByORMID(ID);
    }

    @Override
    public Teacher getTeacherByEmail(String email) throws NonExistentEntityException, PersistentException {
        if(!this.teacherDAO.exists(email))
            throw new NonExistentEntityException();

        return this.teacherDAO.loadTeacherByEmail(email);
    }

    @Override
    public List<Class> getClasses(Teacher teacher) {
        return Arrays.asList(teacher._classes.toArray("abbreviation", true));
    }

    @Override
    public List<Group> getGroups(Teacher teacher) {
        List<Group> groups = new ArrayList<>();
        List<Class> classes = getClasses(teacher);
        for(Class cl: classes){
            for(Group group: cl._groups.toArray())
                groups.add(group);
        }
        return groups;
    }

    @Override
    public Class addClassToTeacher(Teacher teacher, Class cl) throws PersistentException, MissingInformationException, ExistentEntityException {
        if(classService.exists(teacher, cl.getName()))
            throw new ExistentEntityException();
        cl.set_teacher(teacher);
        return classService.addClass(cl);
    }

    @Override
    public boolean exists(int ID) throws PersistentException {
        return this.teacherDAO.exists(ID);
    }

    @Override
    public boolean exists(String email) throws PersistentException {
        return this.teacherDAO.exists(email);
    }

    @Override
    public boolean existsActive(String email) throws PersistentException {
        return this.teacherDAO.existsActive(email);
    }
}
