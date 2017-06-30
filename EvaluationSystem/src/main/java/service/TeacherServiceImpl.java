package service;


import dao.TeacherDAO;
import exception.*;
import model.Class;
import model.Group;
import model.Teacher;
import org.orm.PersistentException;
import org.orm.PersistentSession;
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
    public void delete(PersistentSession session, Teacher teacher) throws PersistentException{
        if(hasStudentSubmissions(session, teacher)){
            teacher.setDeleted(false);
            teacher.setRegistered(true);
            teacherDAO.save(teacher);
        } else {
            for (Class cl : teacher._classes.toArray()) {
                try {
                    classService.delete(session, cl);
                } catch (EntityNotRemovableException e) {
                    throw new PersistentException(); // Nunca deve acontecer
                }
            }

            teacherDAO.delete(teacher);
        }
    }

    @Override
    public boolean hasStudentSubmissions(PersistentSession session, Teacher teacher) throws PersistentException {
        for(Class cl: teacher._classes.toArray()){
            if(classService.classHasSubmissions(session, cl))
                return true;
        }
        return false;
    }

    @Override
    public Teacher addTeacher(PersistentSession session, Teacher teacher, boolean register) throws MissingInformationException, PersistentException, ExistentEntityException {
        Teacher addedTeacher = null;
        try {
            addedTeacher = (Teacher) userService.signup(session, teacher, "teacher", register);
        } catch (InvalidUserTypeException e) {
            e.printStackTrace();
        }
        return addedTeacher;
    }

    @Override
    public Teacher getTeacherByID(PersistentSession session, int ID) throws PersistentException, NonExistentEntityException {
        if(!this.teacherDAO.exists(session, ID))
            throw new NonExistentEntityException();

        return this.teacherDAO.loadTeacherByORMID(session, ID);
    }

    @Override
    public Teacher getTeacherByEmail(PersistentSession session, String email) throws NonExistentEntityException, PersistentException {
        if(!this.teacherDAO.exists(session, email))
            throw new NonExistentEntityException();

        return this.teacherDAO.loadTeacherByEmail(session, email);
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
    public Class addClassToTeacher(PersistentSession session, Teacher teacher, Class cl) throws PersistentException, MissingInformationException, ExistentEntityException {
        if(classService.exists(session, teacher, cl.getName()))
            throw new ExistentEntityException();
        cl.set_teacher(teacher);
        return classService.addClass(cl);
    }

    @Override
    public boolean exists(PersistentSession session, int ID) throws PersistentException {
        return this.teacherDAO.exists(session, ID);
    }

    @Override
    public boolean exists(PersistentSession session, String email) throws PersistentException {
        return this.teacherDAO.exists(session, email);
    }

    @Override
    public boolean existsActive(PersistentSession session, String email) throws PersistentException {
        return this.teacherDAO.existsActive(session, email);
    }
}
