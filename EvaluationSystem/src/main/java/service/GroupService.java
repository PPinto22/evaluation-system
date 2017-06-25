package service;

import exception.ExistentEntityException;
import exception.InvalidUserTypeException;
import exception.NonExistentEntityException;
import model.persistent.*;
import model.persistent.Class;
import org.orm.PersistentException;

import java.util.List;

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

    boolean questionInExam(Group group, Question question);
    List<Question> listAvailableQuestions(Group group) throws PersistentException;
    List<Question> generateExamQuestions(Group group, List<String> categories, List<Integer> difficulties);
}
