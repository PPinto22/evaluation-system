package service;

import model.Student;
import model.Teacher;
import model.User;
import org.orm.PersistentException;

public interface UserService {

    // TODO - Eliminar isto. Apenas para testes
    Student[] getStudents() throws PersistentException;
    Teacher[] getTeachers() throws PersistentException;

    void addStudent(Student student) throws PersistentException;
    void addTeacher(Teacher teacher) throws PersistentException;
    boolean authenticate(User user) throws PersistentException;
}
