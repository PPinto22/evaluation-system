package service;

import exception.ExistentEntityException;
import exception.InvalidUserTypeException;
import exception.MissingInformationException;
import exception.NonExistentEntityException;
import model.Teacher;
import model.Class;
import org.orm.PersistentException;

import java.util.List;

public interface TeacherService {

    void addTeacher(Teacher teacher) throws MissingInformationException, PersistentException, ExistentEntityException, InvalidUserTypeException;
    Teacher getTeacherByID(int ID) throws PersistentException, NonExistentEntityException, InvalidUserTypeException;
    Teacher getTeacherByEmail(String email) throws NonExistentEntityException, PersistentException, InvalidUserTypeException;
    List<Class> getClasses(Teacher teacher);
    void addClassToTeacher(Teacher teacher, Class cl) throws PersistentException, MissingInformationException, ExistentEntityException;
}
