package service;

import exception.ExistentEntityException;
import exception.MissingInformationException;
import exception.NonExistentEntityException;
import model.Class;
import model.Group;
import model.Teacher;
import org.orm.PersistentException;

public interface ClassService {

    Class getClassByID(int id) throws PersistentException, NonExistentEntityException;
    Class getClassByName(Teacher teacher, String className) throws PersistentException, NonExistentEntityException;
    void addClass(Class cl) throws PersistentException, MissingInformationException;
    void delete(Class cl) throws PersistentException;
    boolean exists(int id) throws PersistentException;
    boolean exists(Teacher teacher, String className) throws PersistentException;

    void addGroupToClass(Class cl, Group group) throws PersistentException, ExistentEntityException;
}
