package service;

import exception.*;
import model.persistent.*;
import model.persistent.Class;
import org.orm.PersistentException;

import java.util.List;
import java.util.Map;

public interface GroupService {

    Group getGroupByID(int ID) throws PersistentException, NonExistentEntityException;
    Group getGroupByName(Class cl, String name) throws PersistentException, NonExistentEntityException;
    Group addGroup(Group group) throws PersistentException;
    void delete(Group group) throws PersistentException;
    boolean exists(int ID) throws PersistentException;
    boolean exists(Class cl, String name) throws PersistentException;

    List<GroupStudent> getGroupStudents(Group group);
    GroupStudent addStudentToGroupByEmail(Group group, String email) throws PersistentException, InvalidUserTypeException, ExistentEntityException;
    void removeStudentFromGroup(Group group, Student student) throws PersistentException, NonExistentEntityException;

    boolean studentInGroup(Student student, Group group);
    boolean questionInExams(Group group, Question question) throws PersistentException;
    List<Question> listAvailableQuestions(Group group) throws PersistentException;
    Map<String, Map<Integer, List<Question>>> getAvailableQuestionsByCategoryAndDifficulty(Group group) throws PersistentException;
    List<Question> generateExamQuestions(Group group, List<String> categories, List<Integer> difficulties) throws PersistentException, InvalidInputException, InsufficientQuestionsException;
}
