package service;

import exception.*;
import model.*;
import model.Class;
import org.orm.PersistentException;

import java.util.List;
import java.util.Map;

public interface GroupService {

    Group getGroupByID(int ID) throws PersistentException, NonExistentEntityException;
    Group getGroupByName(Class cl, String name) throws PersistentException, NonExistentEntityException;
    Group addGroup(Group group) throws PersistentException;
    Group updateGroup(Group group, String name) throws PersistentException, ExistentEntityException;
    void delete(Group group) throws PersistentException;
    boolean exists(int ID) throws PersistentException;
    boolean exists(Class cl, String name) throws PersistentException;
    List<GroupStudent> getGroupStudents(Group group);
    GroupStudent addStudentToGroupByEmail(Group group, String email) throws PersistentException, InvalidUserTypeException, ExistentEntityException;
    void removeStudentFromGroup(Group group, Student student) throws PersistentException, NonExistentEntityException;
    boolean userHasAccess(Group group, User user);
    boolean studentInGroup(Student student, Group group);
    boolean questionInExams(Group group, Question question) throws PersistentException;
    List<Question> listAvailableQuestions(Group group) throws PersistentException;
    List<Question> listAvailableQuestionByCategoryAndDifficulty(Group group, String category, int difficulty) throws PersistentException;
    Map<String, Map<Integer, List<Question>>> getAvailableQuestions(Group group) throws PersistentException;

    Map<Student, Map<Exam, Score>> getGroupScores(Group group) throws PersistentException;
}
