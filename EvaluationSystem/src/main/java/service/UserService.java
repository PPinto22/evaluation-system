package service;

import exception.*;
import model.Student;
import model.Teacher;
import model.User;
import org.orm.PersistentException;

public interface UserService {

    // TODO - Eliminar isto. Apenas para testes
    Student[] getStudents() throws PersistentException;
    Teacher[] getTeachers() throws PersistentException;

    User login(String email, String password) throws PersistentException, UnconfirmedEmailException, InvalidUserException;
    User signup(User userDetails, String type) throws PersistentException, MissingInformationException, ExistentEntityException, InvalidUserTypeException;
    void delete(User user) throws PersistentException;

    User getUserByID(int userID) throws PersistentException, NonExistentEntityException;
    User getUserByEmail(String email) throws NonExistentEntityException, PersistentException;

    boolean exists(int ID) throws PersistentException;
    boolean exists(String email) throws PersistentException;
}
