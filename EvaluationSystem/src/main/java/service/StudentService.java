package service;

import exception.ExistentEntityException;
import exception.MissingInformationException;
import exception.NonExistentEntityException;
import model.persistent.Class;
import model.persistent.Group;
import model.persistent.Student;
import org.orm.PersistentException;

import java.util.List;

public interface StudentService {
    Student createStudent();
    Student addStudent(Student student, boolean register) throws MissingInformationException, PersistentException, ExistentEntityException;
    Student getOrCreateStudentByEmail(String email);
    Student getStudentByID(int ID) throws PersistentException, NonExistentEntityException;
    Student getStudentByEmail(String email) throws NonExistentEntityException, PersistentException;
    List<Group> getStudentGroups(Student student);
    List<Class> getStudentClasses(Student student);

    boolean exists(int ID) throws PersistentException;
    boolean exists(String email) throws PersistentException;
    boolean existsActive(String email) throws PersistentException;
}
