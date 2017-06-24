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
    GroupInvitationDAO groupInvitationDAO;

    public NotificationServiceImpl(NotificationDAO notificationDAO, GroupInvitationDAO groupInvitationDAO) {
        this.notificationDAO = notificationDAO;
        this.groupInvitationDAO = groupInvitationDAO;
    }

    @Override
    public Notification getNotificationByID(int id) throws PersistentException, NonExistentEntityException {
        if(!this.notificationDAO.exists(id))
            throw new NonExistentEntityException();

        return this.notificationDAO.loadNotificationByORMID(id);
    }

    @Override
    public GroupInvitation addGroupInvitation(Group group, Student student) throws PersistentException {
        GroupInvitation groupInvitation = new GroupInvitation();
        groupInvitation.set_group(group);
        groupInvitation.set_user(student);
        notificationDAO.save(groupInvitation);
        return groupInvitation;
    }

    @Override
    public GroupInvitation getGroupInvitation(Group group, Student student) throws PersistentException, NonExistentEntityException {
        GroupInvitation groupInvitation = this.groupInvitationDAO.loadGroupInvitationByGroupAndStudent(group,student);
        if(groupInvitation == null)
            throw new NonExistentEntityException();

        return groupInvitation;
    }

    @Override
    public void removeGroupInvitation(GroupInvitation groupInvitation) throws PersistentException {
        Student student = (Student)groupInvitation.get_user();
        student._notifications.remove(groupInvitation);
        this.groupInvitationDAO.delete(groupInvitation);
    }

    @Override
    public void acceptInvitation(GroupInvitation groupInvitation) {
        // TODO acceptInvitation
    }

    @Override
    public void declineInvitation(GroupInvitation groupInvitation) {
        // TODO declineInvitation
    }
}
