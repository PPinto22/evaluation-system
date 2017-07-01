package service;

import exception.*;
import model.User;
import org.orm.PersistentException;
import org.orm.PersistentSession;

public interface UserService {

    User login(PersistentSession session, String email, String password) throws PersistentException, UnconfirmedRegistrationException, InvalidAuthenticationException;
    User signup(PersistentSession session, User userDetails, String type, boolean confirmRegistration) throws PersistentException, MissingInformationException, ExistentEntityException, InvalidUserTypeException;
    User update(User user, String firstName, String lastName, String password) throws PersistentException;
    void delete(PersistentSession session, User user) throws PersistentException;
    void setup(User user, boolean register);


    User getUserByID(PersistentSession session, int userID) throws PersistentException, NonExistentEntityException;
    User getUserByEmail(PersistentSession session, String email, String type) throws NonExistentEntityException, PersistentException, InvalidUserTypeException;

    boolean exists(PersistentSession session, int ID) throws PersistentException;
    boolean exists(PersistentSession session, String email) throws PersistentException;
    boolean existsActive(PersistentSession session, String email) throws PersistentException;
}
