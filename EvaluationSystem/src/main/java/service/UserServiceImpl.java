package service;

import dao.*;
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

    public void addUser(User user){
        try {
            userDAO.save(user);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    public Student[] getStudents(){
        Student[] students = new Student[0];
        try {
            StudentCriteria criteria = new StudentCriteria();
            students = studentDAO.listStudentByCriteria(criteria);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Teacher[] getTeachers(){
        Teacher[] teachers = new Teacher[0];
        try {
            TeacherCriteria criteria = new TeacherCriteria();
            teachers = teacherDAO.listTeacherByCriteria(criteria);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return teachers;
    }
}
