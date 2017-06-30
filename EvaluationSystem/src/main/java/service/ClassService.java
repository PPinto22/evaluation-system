package service;

import exception.*;
import model.Class;
import model.Group;
import model.Question;
import model.Teacher;
import org.orm.PersistentException;
import org.orm.PersistentSession;

import java.util.List;
import java.util.Set;

public interface ClassService {

    Class getClassByID(PersistentSession session, int id) throws PersistentException, NonExistentEntityException;
    Class getClassByName(PersistentSession session, Teacher teacher, String className) throws PersistentException, NonExistentEntityException;
    Class addClass(Class cl) throws PersistentException, MissingInformationException;
    Class updateClass(PersistentSession session, Class cl, String name, String abbreviation) throws PersistentException, ExistentEntityException;
    List<Question> listClassQuestions(PersistentSession session, Class cl) throws PersistentException;
    List<Question> listClassQuestionsByCategoryAndDifficulty(PersistentSession session, Class cl, String category, int difficulty) throws PersistentException;
    Set<String> getClassCategories(PersistentSession session, Class cl) throws PersistentException;
    void delete(PersistentSession session, Class cl) throws PersistentException, EntityNotRemovableException;
    boolean exists(PersistentSession session, int id) throws PersistentException;
    boolean classHasSubmissions(PersistentSession session, Class cl) throws PersistentException;
    boolean exists(PersistentSession session, Teacher teacher, String className) throws PersistentException;
    Group addGroupToClass(PersistentSession session, Class cl, Group group) throws PersistentException, ExistentEntityException;
    Question addQuestionToClass(PersistentSession session, Class cl, Question question) throws InvalidQuestionException, PersistentException, ExistentEntityException;
}
