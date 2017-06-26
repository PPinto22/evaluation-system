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

    User login(String email, String password) throws PersistentException, UnconfirmedRegistrationException, InvalidAuthenticationException;
    User signup(User userDetails, String type, boolean confirmRegistration) throws PersistentException, MissingInformationException, ExistentEntityException, InvalidUserTypeException;
    void delete(User user) throws PersistentException;
    void setup(User user, boolean register);

    User getUserByID(int userID) throws PersistentException, NonExistentEntityException;
    User getUserByEmail(String email, String type) throws NonExistentEntityException, PersistentException, InvalidUserTypeException;

    boolean exists(int ID) throws PersistentException;
    boolean exists(String email) throws PersistentException;
    boolean existsActive(String email) throws PersistentException;
}
