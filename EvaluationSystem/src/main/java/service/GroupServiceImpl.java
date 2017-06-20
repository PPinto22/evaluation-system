package service;

import dao.GroupDAO;
import exception.ExistentEntityException;
import model.Group;
import model.Class;
import org.orm.PersistentException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class GroupServiceImpl implements GroupService{

    GroupDAO groupDAO;

    public GroupServiceImpl(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    public Group getGroupByID(int ID) throws PersistentException, ExistentEntityException {
        if(!this.exists(ID))
            throw new ExistentEntityException();

        else return groupDAO.getGroupByORMID(ID);
    }

    @Override
    public Group getGroupByName(Class cl, String name) throws PersistentException, ExistentEntityException {
        if(!this.exists(cl,name))
            throw new ExistentEntityException();

        else return groupDAO.loadGroupByName(cl, name);
    }

    @Override
    public void addGroup(Group group) throws PersistentException {
        this.groupDAO.save(group);
    }

    @Override
    public boolean exists(int ID) throws PersistentException {
        return this.groupDAO.exists(ID);
    }

    @Override
    public boolean exists(Class cl, String name) throws PersistentException {
        return this.groupDAO.exists(cl, name);
    }
}
