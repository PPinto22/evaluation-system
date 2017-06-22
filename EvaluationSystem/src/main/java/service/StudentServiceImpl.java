package service;

import dao.StudentDAO;
import exception.ExistentEntityException;
import exception.InvalidUserTypeException;
import exception.MissingInformationException;
import exception.NonExistentEntityException;
import model.Student;
import model.User;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    UserService userService;
    StudentDAO studentDAO;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public Student createStudent() {
        Student student = new Student();
        student.setEmail("ND");
        student.setFirstName("ND");
        student.setLastName("ND");
        student.setPassword("ND");
        student.setRegistered(false);
        student.setDeleted(false);
        return student;
    }

    @Override
    public Student addStudent(Student student, boolean register) throws MissingInformationException, PersistentException, ExistentEntityException {
        Student addedStudent = null;
        try {
            addedStudent = (Student) userService.signup(student, "student", register);
        } catch (InvalidUserTypeException e) {
            e.printStackTrace();
        }
        return addedStudent;
    }

    @Override
    public Student getStudentByID(int ID) throws PersistentException, NonExistentEntityException {
        if(!this.studentDAO.exists(ID))
            throw new NonExistentEntityException();

        return this.studentDAO.loadStudentByORMID(ID);
    }

    @Override
    public Student getStudentByEmail(String email) throws NonExistentEntityException, PersistentException {
        if(!this.studentDAO.exists(email))
            throw new NonExistentEntityException();

        return this.studentDAO.loadStudentByEmail(email);
    }


    /*
        Situacoes regulares:
        - Utilizador existe e esta' registado OU
          Utilizador existe mas nao esta' registado (variavel registered = 0)
            -> Retorna utilizador existente
        - Utilizador nao existe OU
          Utilizador existe mas esta' marcado como apagado (variavel deleted = 1)
            -> Cria novo utilizador (com variavel registered = 0) e retorna-o
        Excepcoes:
        - Utilizador existe mas e' um professor
            -> InvalidUserTypeException
     */
    @Override
    public Student getOrCreateStudentByEmail(String email) {
        return null;
    }

    @Override
    public boolean exists(int ID) throws PersistentException {
        return this.studentDAO.exists(ID);
    }

    @Override
    public boolean exists(String email) throws PersistentException {
        return this.studentDAO.exists(email);
    }

    @Override
    public boolean existsActive(String email) throws PersistentException {
        return this.studentDAO.existsActive(email);
    }
}
