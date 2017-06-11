package service;

import dao.*;
import exception.*;
import model.Student;
import model.Teacher;
import model.User;
import org.orm.PersistentException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private StudentDAO studentDAO;
    private UserDAO userDAO;
    private TeacherDAO teacherDAO;

    public UserServiceImpl(StudentDAO studentDAO, UserDAO userDAO, TeacherDAO teacherDAO) {
        this.studentDAO = studentDAO;
        this.userDAO = userDAO;
        this.teacherDAO = teacherDAO;
    }

    @Override
    public User getUserByID(int userID) throws PersistentException {
        return userDAO.getUserByORMID(userID);
    }

    @Override
    public Student[] getStudents() throws PersistentException {
        Student[] students = new Student[0];
        StudentCriteria criteria = new StudentCriteria();
        students = studentDAO.listStudentByCriteria(criteria);
        return students;
    }

    @Override
    public Teacher[] getTeachers() throws PersistentException {
        Teacher[] teachers = new Teacher[0];
        TeacherCriteria criteria = new TeacherCriteria();
        teachers = teacherDAO.listTeacherByCriteria(criteria);
        return teachers;
    }


    @Override
    public User login(String email, String password) throws PersistentException, UnconfirmedEmailException, InvalidUserException {
        password = User.getHash(password);
        User user = userDAO.loadUserByAuthentication(email,password);
        return user;
    }

    @Override
    public User signup(User userDetails, String type)
            throws PersistentException, MissingInformationException, ExistentUserException, InvalidUserTypeException {
        if(userDetails.isMissingInformation() || type == null){
            throw new MissingInformationException();
        }

        userDetails.setDeleted(false);
        userDetails.setRegistered(true); // TODO se for para implementar a verificacao de email, por isto a false
        userDetails.hashPassword();
        if(!userDAO.exists(userDetails.getEmail())){
            switch (type){
                case "student":
                    Student student = new Student(userDetails);
                    studentDAO.save(student);
                    return student;
                case "teacher":
                    Teacher teacher = new Teacher(userDetails);
                    teacherDAO.save(teacher);
                    return teacher;
                default:
                    throw new InvalidUserTypeException();
            }
        }
        else
            throw new ExistentUserException();
    }
}
