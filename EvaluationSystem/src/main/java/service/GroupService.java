package service;

import exception.ExistentEntityException;
import exception.InvalidUserTypeException;
import exception.NonExistentEntityException;
import model.Group;
import model.Class;
import model.GroupStudent;
import org.orm.PersistentException;

public interface GroupService {

    Group getGroupByID(int ID) throws PersistentException, NonExistentEntityException;
    Group getGroupByName(Class cl, String name) throws PersistentException, NonExistentEntityException;
    Group addGroup(Group group) throws PersistentException;
    void delete(Group group) throws PersistentException;
    boolean exists(int ID) throws PersistentException;
    boolean exists(Class cl, String name) throws PersistentException;

    GroupStudent addStudentToGroupByEmail(Group group, String email) throws PersistentException, InvalidUserTypeException, ExistentEntityException;
}
