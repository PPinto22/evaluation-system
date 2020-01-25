package service;

import dao.GroupInvitationDAO;
import dao.GroupStudentDAO;
import dao.NotificationDAO;
import exception.NonExistentEntityException;
import exception.UnconfirmedRegistrationException;
import model.*;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{

    NotificationDAO notificationDAO;
    GroupInvitationDAO groupInvitationDAO;
    GroupStudentDAO groupStudentDAO;

    public NotificationServiceImpl(NotificationDAO notificationDAO, GroupInvitationDAO groupInvitationDAO, GroupStudentDAO groupStudentDAO) {
        this.notificationDAO = notificationDAO;
        this.groupInvitationDAO = groupInvitationDAO;
        this.groupStudentDAO = groupStudentDAO;
    }

    @Override
    public Notification getNotificationByID(PersistentSession session, int id) throws PersistentException, NonExistentEntityException {
        if(!this.notificationDAO.exists(session, id))
            throw new NonExistentEntityException();

        return this.notificationDAO.loadNotificationByORMID(session, id);
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
    public GroupInvitation getGroupInvitation(PersistentSession session, Group group, Student student) throws PersistentException, NonExistentEntityException {
        GroupInvitation groupInvitation = this.groupInvitationDAO.loadGroupInvitationByGroupAndStudent(session, group, student);
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
    public Group acceptInvitation(PersistentSession session, GroupInvitation groupInvitation) throws PersistentException, UnconfirmedRegistrationException {
        GroupStudent groupStudent = groupStudentDAO.loadGroupStudentByGroupAndStudent(session,groupInvitation.get_group().getID(),groupInvitation.get_user().getID());
        Student student = groupStudent.get_student();
        if(!student.isRegistered())
            throw new UnconfirmedRegistrationException();

        groupStudent.setAccepted(true);
        groupStudentDAO.save(groupStudent);
        groupInvitation.get_user()._notifications.remove(groupInvitation);
        notificationDAO.delete(groupInvitation);
        return groupInvitation.get_group();
    }

    @Override
    public void declineInvitation(GroupInvitation groupInvitation) throws PersistentException {
        groupInvitation.get_user()._notifications.remove(groupInvitation);
        notificationDAO.delete(groupInvitation);
    }
}
