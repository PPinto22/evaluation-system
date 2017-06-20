package service;


import dao.TeacherDAO;
import exception.ExistentEntityException;
import exception.InvalidUserTypeException;
import exception.MissingInformationException;
import exception.NonExistentEntityException;
import model.Class;
import model.Student;
import model.Teacher;
import model.User;
import org.orm.PersistentException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService{

    private UserService userService;
    private ClassService classService;
    private TeacherDAO teacherDAO;

    public TeacherServiceImpl(UserService userService, ClassService classService, TeacherDAO teacherDAO) {
        this.userService = userService;
        this.classService = classService;
        this.teacherDAO = teacherDAO;
    }

    @Override
    public void addTeacher(Teacher teacher) throws MissingInformationException, PersistentException, ExistentEntityException {
        if(teacher.isMissingInformation()){
            throw new MissingInformationException();
        }

        teacher.setDeleted(false);
        teacher.setRegistered(true); // TODO se for para implementar a verificacao de email, por isto a false
        teacher.hashPassword();
        if(!userService.exists(teacher.getEmail())){
            teacherDAO.save(teacher);
        }
        else
            throw new ExistentEntityException();
    }

    @Override
    public Teacher getTeacherByID(int ID) throws PersistentException, NonExistentEntityException, InvalidUserTypeException {
        User user = this.userService.getUserByID(ID);
        if(!user.getClass().getSimpleName().equals("Teacher"))
            throw new InvalidUserTypeException();

        return (Teacher)user;
    }

    @Override
    public Teacher getTeacherByEmail(String email) throws NonExistentEntityException, PersistentException, InvalidUserTypeException {
        User user = this.userService.getUserByEmail(email);
        if(!user.getClass().getSimpleName().equals("Teacher"))
            throw new InvalidUserTypeException();

        return (Teacher)user;
    }

    @Override
    public List<Class> getClasses(Teacher teacher) {
        return Arrays.asList(teacher._classes.toArray());
    }

    @Override
    public void addClassToTeacher(Teacher teacher, Class cl) throws PersistentException, MissingInformationException, ExistentEntityException {
        if(classService.exists(teacher, cl.getName()))
            throw new ExistentEntityException();
        cl.set_teacher(teacher);
        classService.addClass(cl);
    }
}
