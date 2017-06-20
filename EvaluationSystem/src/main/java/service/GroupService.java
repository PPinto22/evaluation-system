package service;

import exception.ExistentEntityException;
import model.Group;
import model.Class;
import org.orm.PersistentException;

public interface GroupService {

    Group getGroupByID(int ID) throws PersistentException, ExistentEntityException;
    Group getGroupByName(Class cl, String name) throws PersistentException, ExistentEntityException;
    void addGroup(Group group) throws PersistentException;
    boolean exists(int ID) throws PersistentException;
    boolean exists(Class cl, String name) throws PersistentException;
}
