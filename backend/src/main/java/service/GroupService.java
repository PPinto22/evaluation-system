package service;

import exception.*;
import model.*;
import model.Class;
import org.orm.PersistentException;
import org.orm.PersistentSession;

import java.util.List;
import java.util.Map;

public interface GroupService {

    Group getGroupByID(PersistentSession session, int ID) throws PersistentException, NonExistentEntityException;
    Group getGroupByName(PersistentSession session, Class cl, String name) throws PersistentException, NonExistentEntityException;
    Group addGroup(Group group) throws PersistentException;
    Group updateGroup(PersistentSession session, Group group, String name) throws PersistentException, ExistentEntityException;
    void delete(PersistentSession session, Group group) throws PersistentException, EntityNotRemovableException;
    boolean exists(PersistentSession session, int ID) throws PersistentException;
    boolean exists(PersistentSession session, Class cl, String name) throws PersistentException;
    boolean groupHasSubmissions(PersistentSession session, Group group) throws PersistentException;
    List<GroupStudent> getGroupStudents(Group group);
    List<Student> getAcceptedStudents(Group group);
    GroupStudent addStudentToGroupByEmail(PersistentSession session, Group group, String email) throws PersistentException, InvalidUserTypeException, ExistentEntityException;
    void removeStudentFromGroup(PersistentSession session, Group group, Student student) throws PersistentException, NonExistentEntityException, EntityNotRemovableException;
    boolean userHasAccess(Group group, User user);
    boolean studentInGroup(Student student, Group group);
    boolean questionInExams(PersistentSession session, Group group, Question question) throws PersistentException;
    List<Question> listAvailableQuestions(PersistentSession session, Group group) throws PersistentException;
    List<Question> listAvailableQuestionByCategoryAndDifficulty(PersistentSession session, Group group, String category, int difficulty) throws PersistentException;
    Map<String, Map<Integer, List<Question>>> getAvailableQuestions(PersistentSession session, Group group) throws PersistentException;

    Map<Student, Map<Exam, Score>> getGroupScores(PersistentSession session, Group group) throws PersistentException;
}
