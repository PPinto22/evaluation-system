package service;

import dao.*;
import exception.*;
import model.Student;
import model.Teacher;
import model.User;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private StudentDAO studentDAO;
    private UserDAO userDAO;
    private TeacherDAO teacherDAO;
    private StudentService studentService;
    private TeacherService teacherService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    public UserServiceImpl(StudentDAO studentDAO, UserDAO userDAO, TeacherDAO teacherDAO) {
        this.studentDAO = studentDAO;
        this.userDAO = userDAO;
        this.teacherDAO = teacherDAO;
    }

    @Override
    public User getUserByID(int userID) throws PersistentException, NonExistentEntityException {
        if(!this.userDAO.exists(userID))
            throw new NonExistentEntityException();

        return userDAO.getUserByORMID(userID);
    }

    @Override
    public User getUserByEmail(String email, String type) throws NonExistentEntityException, PersistentException, InvalidUserTypeException {
        switch (type.toLowerCase()){
            case "student":
                return this.studentService.getStudentByEmail(email);
            case "teacher":
                return this.teacherService.getTeacherByEmail(email);
            default:
                throw new InvalidUserTypeException();
        }
    }

    @Override
    public User login(String email, String password) throws PersistentException, InvalidAuthenticationException, UnconfirmedRegistrationException {
        password = User.getHash(password);
        if(!userDAO.exists(email)){
            throw new InvalidAuthenticationException();
        }

        User user = null;
        try {
            user = userDAO.loadUserByAuthentication(email,password);
        } catch (UnconfirmedRegistrationException e) {
            throw new UnconfirmedRegistrationException();
        } catch (InvalidUserException e) {
            throw new InvalidAuthenticationException();
        }
        if(!user.isRegistered() || !user.getPassword().equals(password)){
            throw new InvalidAuthenticationException();
        }
        return user;
    }

    @Override
    public User signup(User userDetails, String type, boolean confirmRegistration)
            throws PersistentException, MissingInformationException, ExistentEntityException, InvalidUserTypeException {
        if(userDetails.isMissingInformation() || type == null){
            throw new MissingInformationException();
        }

        if(this.existsActive(userDetails.getEmail()))
            throw new ExistentEntityException();

        try {
            User user = this.getUserByEmail(userDetails.getEmail(), type);
            return this.setupAndUpdate(userDetails, type, confirmRegistration);
        } catch (NonExistentEntityException e) {
            return this.setupAndSave(userDetails,type, confirmRegistration);
        }
    }

    private User setupAndUpdate(User userDetails, String type, boolean register) throws NonExistentEntityException, PersistentException {
        User user = null;
        try {
            user = this.getUserByEmail(userDetails.getEmail(), type);
        } catch (InvalidUserTypeException e) {
            e.printStackTrace();
        }
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setPassword(userDetails.getPassword());
        this.setup(user, register);
        userDAO.save(user);
        return user;
    }

    private User setupAndSave(User user, String type, boolean register) throws PersistentException, InvalidUserTypeException {
        this.setup(user,register);
        switch (type.toLowerCase()){
            case "student":
                Student student = new Student(user);
                studentDAO.save(student);
                return student;
            case "teacher":
                Teacher teacher = new Teacher(user);
                teacherDAO.save(teacher);
                return teacher;
            default:
                throw new InvalidUserTypeException();
        }
    }

    @Override
    public void setup(User user, boolean register) {
        user.setDeleted(false);
        user.setRegistered(register);
        user.hashPassword();
        user.setRegistrationCode(UUID.randomUUID().toString());
    }

    @Override
    public void delete(User user) throws PersistentException {
        userDAO.delete(user);
    }

    @Override
    public boolean exists(int ID) throws PersistentException {
        return this.userDAO.exists(ID);
    }

    @Override
    public boolean exists(String email) throws PersistentException {
        return this.userDAO.exists(email);
    }

    public boolean existsActive(String email) throws PersistentException {
        return this.userDAO.existsActive(email);
    }
}
