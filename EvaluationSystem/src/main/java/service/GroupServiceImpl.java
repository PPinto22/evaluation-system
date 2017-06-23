package service;

import dao.GroupDAO;
import dao.GroupStudentDAO;
import dao.StudentDAO;
import exception.ExistentEntityException;
import exception.InvalidUserTypeException;
import exception.MissingInformationException;
import exception.NonExistentEntityException;
import model.*;
import model.Class;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{

    GroupDAO groupDAO;
    GroupStudentDAO groupStudentDAO;
    UserService userService;
    StudentService studentService;
    NotificationService notificationService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public GroupServiceImpl(GroupDAO groupDAO, GroupStudentDAO groupStudentDAO) {
        this.groupDAO = groupDAO;
        this.groupStudentDAO = groupStudentDAO;
    }

    @Override
    public Group getGroupByID(int ID) throws PersistentException, NonExistentEntityException {
        if(!this.exists(ID))
            throw new NonExistentEntityException();

        else return groupDAO.getGroupByORMID(ID);
    }

    @Override
    public Group getGroupByName(Class cl, String name) throws PersistentException, NonExistentEntityException {
        if(!this.exists(cl,name))
            throw new NonExistentEntityException();

        else return groupDAO.loadGroupByName(cl, name);
    }

    @Override
    public Group addGroup(Group group) throws PersistentException {
        this.groupDAO.save(group);
        return group;
    }

    @Override
    public void delete(Group group) throws PersistentException {
        Class cl = group.get_class();
        cl._groups.remove(group);
        this.groupDAO.delete(group);
    }

    @Override
    public List<GroupStudent> getGroupStudents(Group group) {
        return Arrays.asList(group._students.toArray());
    }

    @Override
    public GroupStudent addStudentToGroupByEmail(Group group, String email)
            throws PersistentException, InvalidUserTypeException, ExistentEntityException {
        Student student = null;
        if(!userService.existsActive(email)){
            if(!userService.exists(email)) {
                try {
                    student = studentService.createStudent();
                    student.setEmail(email);
                    student = studentService.addStudent(student, false);
                } catch (MissingInformationException e) {
                    e.printStackTrace(); // Nunca acontece
                } catch (ExistentEntityException e) {
                    e.printStackTrace();
                    throw new PersistentException(); // Nunca deve acontecer
                }
            }
            else{
                try {
                    student = studentService.getStudentByEmail(email);
                } catch (NonExistentEntityException e) {
                    e.printStackTrace(); //Nunca deve acontecer
                }
            }
        }
        else{
            if(studentService.existsActive(email)) {
                try {
                    student = studentService.getStudentByEmail(email);
                } catch (NonExistentEntityException e) {
                    e.printStackTrace(); // Nunca deve acontecer
                }
            }
            else
                throw new InvalidUserTypeException();
        }

        if(groupStudentDAO.exists(group.getID(), student.getID()))
            throw new ExistentEntityException();
        return this.addStudentToGroup(group,student);
    }

    private GroupStudent addStudentToGroup(Group group, Student student) throws PersistentException {
        GroupStudent groupStudent = this.createGroupStudent(group,student);
        this.groupStudentDAO.save(groupStudent);
        this.notificationService.addGroupInvitation(group, student);
        return groupStudent;
    }

    @Override
    public void removeStudentFromGroup(Group group, Student student) throws PersistentException, NonExistentEntityException {
        GroupStudent groupStudent = this.groupStudentDAO.loadGroupStudentByGroupAndStudent(
                group.getID(), student.getID());
        if(groupStudent == null)
            throw new NonExistentEntityException();

        group._students.remove(groupStudent);
        this.groupStudentDAO.delete(groupStudent);
        GroupInvitation groupInvitation = notificationService.getGroupInvitation(group,student);
        notificationService.removeGroupInvitation(groupInvitation);
    }

    private GroupStudent createGroupStudent(Group group, Student student){
        GroupStudent groupStudent = new GroupStudent();
        groupStudent.set_group(group);
        groupStudent.set_student(student);
        groupStudent.setAccepted(false);
        return groupStudent;
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
