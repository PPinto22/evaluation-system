package service;

import dao.GroupInvitationDAO;
import dao.NotificationDAO;
import exception.NonExistentEntityException;
import model.Group;
import model.GroupInvitation;
import model.Notification;
import model.Student;
import org.orm.PersistentException;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{

    NotificationDAO notificationDAO;

    public NotificationServiceImpl(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }

    @Override
    public Notification getNotificationByID(int id) throws PersistentException, NonExistentEntityException {
        if(!this.notificationDAO.exists(id))
            throw new NonExistentEntityException();

        return this.notificationDAO.loadNotificationByORMID(id);
    }

    @Override
    public GroupInvitation addGroupInvitation(Group group, Student student) {
        return null;
    }

    @Override
    public void acceptInvitation(GroupInvitation groupInvitation) {

    }

    @Override
    public void declineInvitation(GroupInvitation groupInvitation) {

    }
}
