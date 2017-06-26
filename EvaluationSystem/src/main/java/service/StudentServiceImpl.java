package service;

import dao.StudentDAO;
import exception.ExistentEntityException;
import exception.InvalidUserTypeException;
import exception.MissingInformationException;
import exception.NonExistentEntityException;
import model.persistent.Class;
import model.persistent.Group;
import model.persistent.GroupStudent;
import model.persistent.Student;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wrapper.GroupClassWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public List<Group> getStudentGroups(Student student) {
        List<Group> groups = new ArrayList<>();
        List<GroupStudent> groupStudentList = Arrays.asList(student._groups.toArray());
        for(GroupStudent groupStudent: groupStudentList) {
            if (groupStudent.isAccepted()) {
                groups.add(groupStudent.get_group());
            }
        }
        return groups;
    }

    @Override
    public List<Class> getStudentClasses(Student student) {
        List<Class> classes = new ArrayList<>();
        List<Group> groups = getStudentGroups(student);
        for(Group group: groups){
            Class cl = group.get_class();
            if(!classes.contains(cl))
                classes.add(cl);
        }
        return classes;
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
