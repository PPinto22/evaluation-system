package service;

import exception.*;
import model.*;
import model.Class;
import org.orm.PersistentException;

import java.util.List;
import java.util.Map;

public interface StudentService {
    Student createStudent();
    Student addStudent(Student student, boolean register) throws MissingInformationException, PersistentException, ExistentEntityException;
    Student getOrCreateStudentByEmail(String email);
    Student getStudentByID(int ID) throws PersistentException, NonExistentEntityException;
    Student getStudentByEmail(String email) throws NonExistentEntityException, PersistentException;
    List<Group> getStudentGroups(Student student);
    List<Class> getStudentClasses(Student student);

    Map<Group, Map<Exam, Score>> getStudentScores(Student student) throws PersistentException;
    Map<Exam, Score> getStudentScoresByGroup(Student student, Group group) throws StudentNotInGroupException, PersistentException;
    Score getStudentScoreByExam(Student student, Exam exam) throws PersistentException, StudentNotInGroupException, InvalidExamException;
    boolean exists(int ID) throws PersistentException;
    boolean exists(String email) throws PersistentException;
    boolean existsActive(String email) throws PersistentException;
}
