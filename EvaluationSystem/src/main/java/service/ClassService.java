package service;

import exception.NonExistentEntityException;
import model.Class;
import org.orm.PersistentException;

public interface ClassService {

    Class getClassByID(int id) throws PersistentException, NonExistentEntityException;

    void addClass(Class cl) throws PersistentException;
    void delete(Class cl) throws PersistentException;
}
