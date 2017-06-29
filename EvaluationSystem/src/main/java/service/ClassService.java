package service;

import exception.ExistentEntityException;
import exception.InvalidQuestionException;
import exception.MissingInformationException;
import exception.NonExistentEntityException;
import model.Class;
import model.Group;
import model.Question;
import model.Teacher;
import org.orm.PersistentException;

import java.util.List;
import java.util.Set;

public interface ClassService {

    Class getClassByID(int id) throws PersistentException, NonExistentEntityException;
    Class getClassByName(Teacher teacher, String className) throws PersistentException, NonExistentEntityException;
    Class addClass(Class cl) throws PersistentException, MissingInformationException;
    List<Question> listClassQuestions(Class cl) throws PersistentException;
    Set<String> getClassCategories(Class cl) throws PersistentException;
    void delete(Class cl) throws PersistentException;
    boolean exists(int id) throws PersistentException;

    boolean exists(Teacher teacher, String className) throws PersistentException;
    Group addGroupToClass(Class cl, Group group) throws PersistentException, ExistentEntityException;

    Question addQuestionToClass(Class cl, Question question) throws InvalidQuestionException, PersistentException, ExistentEntityException;
}
