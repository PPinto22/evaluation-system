package service;

import dao.ClassDAO;
import dao.TeacherDAO;
import dao.UserDAO;
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
    public void addClass(Class cl) throws PersistentException {
        classDAO.save(cl);
    }

    @Override
    public void delete(Class cl) throws PersistentException {
        Teacher teacher = cl.get_teacher();
        teacher._classes.remove(cl);
        classDAO.delete(cl);
    }
}
