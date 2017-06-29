package service;

import exception.ExistentEntityException;
import exception.MissingInformationException;
import exception.NonExistentEntityException;
import model.Group;
import model.Teacher;
import model.Class;
import org.orm.PersistentException;

import java.util.List;

public interface TeacherService {

    Teacher addTeacher(Teacher teacher, boolean register) throws MissingInformationException, PersistentException, ExistentEntityException;
    Teacher getTeacherByID(int ID) throws PersistentException, NonExistentEntityException;
    Teacher getTeacherByEmail(String email) throws NonExistentEntityException, PersistentException;
    List<Class> getClasses(Teacher teacher);
    List<Group> getGroups(Teacher teacher);
    Class addClassToTeacher(Teacher teacher, Class cl) throws PersistentException, MissingInformationException, ExistentEntityException;

    boolean exists(int ID) throws PersistentException;
    boolean exists(String email) throws PersistentException;
    boolean existsActive(String email) throws PersistentException;
}
