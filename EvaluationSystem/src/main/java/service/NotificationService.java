package service;

import exception.NonExistentEntityException;
import model.Group;
import model.GroupInvitation;
import model.Notification;
import model.Student;
import org.orm.PersistentException;

public interface NotificationService {

    Notification getNotificationByID(int id) throws PersistentException, NonExistentEntityException;
    GroupInvitation addGroupInvitation(Group group, Student student);
    void acceptInvitation(GroupInvitation groupInvitation);
    void declineInvitation(GroupInvitation groupInvitation);

}
