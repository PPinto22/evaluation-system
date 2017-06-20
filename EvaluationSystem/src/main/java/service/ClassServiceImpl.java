package service;

import dao.ClassDAO;
import exception.MissingInformationException;
import exception.NonExistentEntityException;
import model.Class;
import model.Teacher;
import org.orm.PersistentException;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements ClassService{

    ClassDAO classDAO;

    public ClassServiceImpl(ClassDAO classDAO) {
        this.classDAO = classDAO;
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
    public void addClass(Class cl) throws PersistentException, MissingInformationException {
        if(cl.missingInformation())
            throw new MissingInformationException();
        this.classDAO.save(cl);
    }

    @Override
    public void delete(Class cl) throws PersistentException {
        Teacher teacher = cl.get_teacher();
        teacher._classes.remove(cl);
        classDAO.delete(cl);
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
