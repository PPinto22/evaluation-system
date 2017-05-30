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

//    public Student[] getStudents() throws PersistentException {
//        Student[] students = new Student[0];
//        StudentCriteria criteria = new StudentCriteria();
//        students = studentDAO.listStudentByCriteria(criteria);
//        return students;
//    }
//
//    public Teacher[] getTeachers() throws PersistentException {
//        Teacher[] teachers = new Teacher[0];
//        TeacherCriteria criteria = new TeacherCriteria();
//        teachers = teacherDAO.listTeacherByCriteria(criteria);
//        return teachers;
//    }

    @Override
    public void addStudent(Student student) throws PersistentException {
        // TODO - Validar informacoes, verificar nao repetido, etc...
        student.hashPassword();
        studentDAO.save(student);
    }

    @Override
    public void addTeacher(Teacher teacher) throws PersistentException {
        // TODO - Validar informacoes, verificar nao repetido, etc...
        teacher.hashPassword();
        teacherDAO.save(teacher);
    }

    @Override
    public boolean authenticate(User user) throws PersistentException {
        User persistedUser = userDAO.loadUserByEmail(user.getEmail());
        return user.getPasswordHash().equals(persistedUser.getPassword());
    }
}
