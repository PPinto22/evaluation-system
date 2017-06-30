package service;

import exception.*;
import model.*;
import model.Class;
import org.orm.PersistentException;
import org.orm.PersistentSession;

import java.util.List;
import java.util.Map;

public interface StudentService {
    Student createStudent();
    Student addStudent(PersistentSession session, Student student, boolean register) throws MissingInformationException, PersistentException, ExistentEntityException;
    Student getStudentByID(PersistentSession session, int ID) throws PersistentException, NonExistentEntityException;
    Student getStudentByEmail(PersistentSession session, String email) throws NonExistentEntityException, PersistentException;
    List<Group> getStudentGroups(Student student);
    List<Class> getStudentClasses(Student student);

    Map<Group, Map<Exam, Score>> getStudentScores(PersistentSession session, Student student) throws PersistentException;
    Map<Exam, Score> getStudentScoresByGroup(PersistentSession session, Student student, Group group) throws StudentNotInGroupException, PersistentException;
    Score getStudentScoreByExam(PersistentSession session, Student student, Exam exam) throws PersistentException, StudentNotInGroupException, InvalidExamException;
    boolean exists(PersistentSession session, int ID) throws PersistentException;
    boolean exists(PersistentSession session, String email) throws PersistentException;
    boolean existsActive(PersistentSession session, String email) throws PersistentException;

    boolean inAGroup(Student student);
    void leaveGroup(PersistentSession session, Student student, Group group) throws PersistentException, StudentNotInGroupException;
    void delete(PersistentSession session, Student student) throws PersistentException;
}
