package service;

import exception.NonExistentEntityException;
import exception.UnconfirmedRegistrationException;
import model.Group;
import model.GroupInvitation;
import model.Notification;
import model.Student;
import org.orm.PersistentException;
import org.orm.PersistentSession;

public interface NotificationService {

    Notification getNotificationByID(PersistentSession session, int id) throws PersistentException, NonExistentEntityException;
    GroupInvitation getGroupInvitation(PersistentSession session, Group group, Student student) throws PersistentException, NonExistentEntityException;
    GroupInvitation addGroupInvitation(Group group, Student student) throws PersistentException;
    void removeGroupInvitation(GroupInvitation groupInvitation) throws PersistentException;
    Group acceptInvitation(PersistentSession session, GroupInvitation groupInvitation) throws PersistentException, UnconfirmedRegistrationException;
    void declineInvitation(GroupInvitation groupInvitation) throws PersistentException;

}
