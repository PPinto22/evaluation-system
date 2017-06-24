package service;

import exception.ExistentEntityException;
import exception.InvalidUserTypeException;
import exception.MissingInformationException;
import exception.NonExistentEntityException;
import model.Student;
import org.orm.PersistentException;

public interface StudentService {
    Student createStudent();
    Student addStudent(Student student, boolean register) throws MissingInformationException, PersistentException, ExistentEntityException;
    Student getOrCreateStudentByEmail(String email);
    Student getStudentByID(int ID) throws PersistentException, NonExistentEntityException;
    Student getStudentByEmail(String email) throws NonExistentEntityException, PersistentException;

    boolean exists(int ID) throws PersistentException;
    boolean exists(String email) throws PersistentException;
    boolean existsActive(String email) throws PersistentException;
}
