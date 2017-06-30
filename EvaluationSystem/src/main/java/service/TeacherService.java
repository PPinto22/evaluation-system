package service;

import exception.ExistentEntityException;
import exception.MissingInformationException;
import exception.NonExistentEntityException;
import model.Group;
import model.Teacher;
import model.Class;
import org.orm.PersistentException;
import org.orm.PersistentSession;

import java.util.List;

public interface TeacherService {

    Teacher addTeacher(PersistentSession session, Teacher teacher, boolean register) throws MissingInformationException, PersistentException, ExistentEntityException;
    Teacher getTeacherByID(PersistentSession session, int ID) throws PersistentException, NonExistentEntityException;
    Teacher getTeacherByEmail(PersistentSession session, String email) throws NonExistentEntityException, PersistentException;
    List<Class> getClasses(Teacher teacher);
    List<Group> getGroups(Teacher teacher);
    Class addClassToTeacher(PersistentSession session, Teacher teacher, Class cl) throws PersistentException, MissingInformationException, ExistentEntityException;

    boolean exists(PersistentSession session, int ID) throws PersistentException;
    boolean exists(PersistentSession session, String email) throws PersistentException;
    boolean existsActive(PersistentSession session, String email) throws PersistentException;

    void delete(PersistentSession session, Teacher teacher) throws PersistentException;
    boolean hasStudentSubmissions(PersistentSession session, Teacher teacher) throws PersistentException;
}
