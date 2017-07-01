package service;

import dao.*;
import exception.*;
import model.*;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
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
    public User getUserByID(PersistentSession session, int userID) throws PersistentException, NonExistentEntityException {
        if(!this.userDAO.exists(session, userID))
            throw new NonExistentEntityException();

        return userDAO.getUserByORMID(session, userID);
    }

    @Override
    public User getUserByEmail(PersistentSession session, String email, String type) throws NonExistentEntityException, PersistentException, InvalidUserTypeException {
        switch (type.toLowerCase()){
            case "student":
                return this.studentService.getStudentByEmail(session, email);
            case "teacher":
                return this.teacherService.getTeacherByEmail(session, email);
            default:
                throw new InvalidUserTypeException();
        }
    }

    @Override
    public User update(User user, String firstName, String lastName, String password) throws PersistentException {
        if(firstName != null && !firstName.equals(""))
            user.setFirstName(firstName);
        if(lastName != null && !lastName.equals(""))
            user.setLastName(lastName);
        if(password != null && !password.equals("")) {
            user.setPassword(password);
            user.hashPassword();
        }
        userDAO.save(user);
        return user;
    }

    @Override
    public Map<Group, Map<Exam, Score>> getUserScores(PersistentSession session, User user) throws PersistentException {
        switch (user.getClass().getSimpleName()){
            case "Student":
                return studentService.getStudentScores(session,(Student)user);
            case "Teacher":
                return teacherService.getScores(session, (Teacher)user);
        }
        throw new PersistentException();
    }

    @Override
    public Map<Exam, Score> getUserScoresByGroup(PersistentSession session, User user, Group group) throws UserNotInGroupException, PersistentException {
        switch (user.getClass().getSimpleName()){
            case "Student":
                return studentService.getStudentScoresByGroup(session,(Student)user, group);
            case "Teacher":
                return teacherService.getScoresByGroup(session, (Teacher)user, group);
        }
        throw new PersistentException();
    }

    @Override
    public Score getExamScore(PersistentSession session, User user, Exam exam) throws PersistentException, UserNotInGroupException, InvalidExamException {
        switch (user.getClass().getSimpleName()){
            case "Student":
                return studentService.getStudentScoreByExam(session,(Student)user, exam);
            case "Teacher":
                return teacherService.getScoreByExam(session, (Teacher)user, exam);
        }
        throw new PersistentException();
    }

    @Override
    public User login(PersistentSession session, String email, String password) throws PersistentException, InvalidAuthenticationException, UnconfirmedRegistrationException {
        password = User.getHash(password);
        if(!userDAO.exists(session, email)){
            throw new InvalidAuthenticationException();
        }

        User user = null;
        try {
            user = userDAO.loadUserByAuthentication(session, email, password);
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
    public User signup(PersistentSession session, User userDetails, String type, boolean confirmRegistration)
            throws PersistentException, MissingInformationException, ExistentEntityException, InvalidUserTypeException {
        if(userDetails.isMissingInformation() || type == null){
            throw new MissingInformationException();
        }

        if(this.existsActive(session, userDetails.getEmail()))
            throw new ExistentEntityException();

        try {
            User user = this.getUserByEmail(session, userDetails.getEmail(), type);
            return this.setupAndUpdate(session, userDetails, type, confirmRegistration);
        } catch (NonExistentEntityException e) {
            return this.setupAndSave(userDetails,type, confirmRegistration);
        }
    }

    private User setupAndUpdate(PersistentSession session, User userDetails, String type, boolean register) throws NonExistentEntityException, PersistentException {
        User user = null;
        try {
            user = this.getUserByEmail(session, userDetails.getEmail(), type);
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
    public void delete(PersistentSession session, User user) throws PersistentException {
        switch (user.getClass().getSimpleName()){
            case "Teacher":
                teacherService.delete(session, (Teacher)user);
                break;
            case "Student":
                studentService.delete(session, (Student)user);
                break;
        }
    }

    @Override
    public boolean exists(PersistentSession session, int ID) throws PersistentException {
        return this.userDAO.exists(session, ID);
    }

    @Override
    public boolean exists(PersistentSession session, String email) throws PersistentException {
        return this.userDAO.exists(session, email);
    }

    public boolean existsActive(PersistentSession session, String email) throws PersistentException {
        return this.userDAO.existsActive(session, email);
    }
}
