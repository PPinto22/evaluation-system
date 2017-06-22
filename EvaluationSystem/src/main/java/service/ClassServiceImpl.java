package service;

import dao.ClassDAO;
import dao.TeacherDAO;
import exception.ExistentEntityException;
import exception.MissingInformationException;
import exception.NonExistentEntityException;
import model.Class;
import model.Group;
import model.Teacher;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements ClassService{

    ClassDAO classDAO;
    TeacherDAO teacherDAO;
    GroupService groupService;
    TeacherService teacherService;

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }
    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public ClassServiceImpl(ClassDAO classDAO, TeacherDAO teacherDAO) {
        this.classDAO = classDAO;
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Class getClassByID(int id) throws PersistentException, NonExistentEntityException {
        if(!classDAO.exists(id))
            throw new NonExistentEntityException();
        return this.classDAO.loadClassByORMID(id);
    }

    @Override
    public Class getClassByName(Teacher teacher, String className) throws PersistentException, NonExistentEntityException {
        if(!classDAO.exists(teacher.getID(), className))
            throw new NonExistentEntityException();

        return this.classDAO.getClassByName(teacher.getID(),className);
    }

    @Override
    public Class addClass(Class cl) throws PersistentException, MissingInformationException {
        if(cl.isMissingInformation())
            throw new MissingInformationException();
        this.classDAO.save(cl);
        return cl;
    }

    @Override
    public Group addGroupToClass(Class cl, Group group) throws PersistentException, ExistentEntityException {
        if(this.groupService.exists(cl, group.getName()))
            throw new ExistentEntityException();

        cl._groups.add(group);
        group.set_class(cl);
        return this.groupService.addGroup(group);
    }

    @Override
    public void delete(Class cl) throws PersistentException {
        Teacher teacher = cl.get_teacher();
        teacher._classes.remove(cl);
        this.classDAO.delete(cl);
    }

    @Override
    public boolean exists(int id) throws PersistentException {
        return this.classDAO.exists(id);
    }

    @Override
    public boolean exists(Teacher teacher, String className) throws PersistentException {
        return this.classDAO.exists(teacher.getID(), className);
    }
}
