package service;

import exception.NonExistentEntityException;
import exception.UnconfirmedRegistrationException;
import model.persistent.Group;
import model.persistent.GroupInvitation;
import model.persistent.Notification;
import model.persistent.Student;
import org.orm.PersistentException;

public interface NotificationService {

    Notification getNotificationByID(int id) throws PersistentException, NonExistentEntityException;
    GroupInvitation getGroupInvitation(Group group, Student student) throws PersistentException, NonExistentEntityException;
    GroupInvitation addGroupInvitation(Group group, Student student) throws PersistentException;
    void removeGroupInvitation(GroupInvitation groupInvitation) throws PersistentException;
    Group acceptInvitation(GroupInvitation groupInvitation) throws PersistentException, UnconfirmedRegistrationException;
    void declineInvitation(GroupInvitation groupInvitation) throws PersistentException;

}
