package service;

import dao.GroupStudentDAO;
import dao.NotificationDAO;
import dao.StudentDAO;
import exception.*;
import model.*;
import model.Class;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService{

    UserService userService;
    SubmissionService submissionService;
    GroupService groupService;
    ExamService examService;
    NotificationService notificationService;
    StudentDAO studentDAO;
    GroupStudentDAO groupStudentDAO;
    NotificationDAO notificationDAO;

    @Autowired
    public void setExamService(ExamService examService) {
        this.examService = examService;
    }
    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }
    @Autowired
    public void setSubmissionService(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public StudentServiceImpl(StudentDAO studentDAO, GroupStudentDAO groupStudentDAO, NotificationDAO notificationDAO) {
        this.studentDAO = studentDAO;
        this.groupStudentDAO = groupStudentDAO;
        this.notificationDAO = notificationDAO;
    }

    @Override
    public boolean inAGroup(Student student) {
        return !student._groups.isEmpty();
    }

    @Override
    public void delete(PersistentSession session, Student student) throws PersistentException {
        if(inAGroup(student)){
            for(GroupStudent groupStudent: student._groups.toArray()){
                try {
                    leaveGroup(session, student, groupStudent.get_group());
                } catch (UserNotInGroupException e) {}
            }
            student.setRegistered(false);
            student.setDeleted(true);
            studentDAO.save(student);
        } else {
            for(Notification notification: student._notifications.toArray()){
                student._notifications.remove(notification);
                notificationDAO.delete(notification);
            }
            studentDAO.delete(student);
        }
    }

    @Override
    public void leaveGroup(PersistentSession session, Student student, Group group) throws PersistentException, UserNotInGroupException {
        if(!groupStudentDAO.exists(session, group.getID(), student.getID()))
            throw new UserNotInGroupException();
        GroupStudent groupStudent = groupStudentDAO.loadGroupStudentByGroupAndStudent(session, group.getID(), student.getID());
        groupStudent.setAccepted(false);
        groupStudentDAO.save(groupStudent);

        try{
            GroupInvitation groupInvitation = notificationService.getGroupInvitation(session, group, student);
            notificationService.removeGroupInvitation(groupInvitation);
        } catch (NonExistentEntityException e) {}
    }

    @Override
    public Map<Group, Map<Exam, Score>> getStudentScores(PersistentSession session, Student student) throws PersistentException {
        Map<Group, Map<Exam, Score>> groupMap = new TreeMap<>();
        for(Group group: getStudentGroups(student)){
            Map<Exam, Score> examMap = new TreeMap<>();
            for(Exam exam: group.getExams()){
                if(examService.examHasFinished(exam)) {
                    try {
                        Submission submission = submissionService.getSubmissionByStudentAndExam(session, student, exam);
                        examMap.put(exam, new Score(submission));
                    } catch (NonExistentEntityException e) {
                        examMap.put(exam, new Score());
                    }
                }
            }
            if(!examMap.isEmpty())
                groupMap.put(group, examMap);
        }
        return groupMap;
    }

    @Override
    public Map<Exam, Score> getStudentScoresByGroup(PersistentSession session, Student student, Group group)
            throws UserNotInGroupException, PersistentException {
        if(!groupService.studentInGroup(student,group))
            throw new UserNotInGroupException();

        Map<Exam, Score> scoreMap = new TreeMap<>();
        for(Exam exam: group.getExams()){
            if(examService.examHasFinished(exam)) {
                try {
                    Submission submission = submissionService.getSubmissionByStudentAndExam(session, student, exam);
                    scoreMap.put(exam, new Score(submission));
                } catch (NonExistentEntityException e) {
                    scoreMap.put(exam, new Score());
                }
            }
        }

        return scoreMap;
    }

    @Override
    public Score getStudentScoreByExam(PersistentSession session, Student student, Exam exam)
            throws PersistentException, UserNotInGroupException, InvalidExamException {
        if(!examService.examHasFinished(exam))
            throw new InvalidExamException();
        if(!groupService.studentInGroup(student,exam.get_group()))
            throw new UserNotInGroupException();
        Score score = null;
        try {
            Submission submission = submissionService.getSubmissionByStudentAndExam(session, student, exam);
            score = new Score(submission);
        } catch (NonExistentEntityException e) {
            score = new Score();
        }
        return score;
    }

    @Override
    public Student createStudent() {
        Student student = new Student();
        student.setEmail("ND");
        student.setFirstName("ND");
        student.setLastName("ND");
        student.setPassword("ND");
        student.setRegistered(false);
        student.setDeleted(false);
        return student;
    }

    @Override
    public List<Group> getStudentGroups(Student student) {
        List<Group> groups = new ArrayList<>();
        List<GroupStudent> groupStudentList = Arrays.asList(student._groups.toArray());
        for(GroupStudent groupStudent: groupStudentList) {
            if (groupStudent.isAccepted()) {
                groups.add(groupStudent.get_group());
            }
        }
        return groups;
    }

    @Override
    public List<Class> getStudentClasses(Student student) {
        List<Class> classes = new ArrayList<>();
        List<Group> groups = getStudentGroups(student);
        for(Group group: groups){
            Class cl = group.get_class();
            if(!classes.contains(cl))
                classes.add(cl);
        }
        return classes;
    }

    @Override
    public Student addStudent(PersistentSession session, Student student, boolean register)
            throws MissingInformationException, PersistentException, ExistentEntityException {
        Student addedStudent = null;
        try {
            addedStudent = (Student) userService.signup(session, student, "student", register);
        } catch (InvalidUserTypeException e) {
            e.printStackTrace();
        }
        return addedStudent;
    }

    @Override
    public Student getStudentByID(PersistentSession session, int ID) throws PersistentException, NonExistentEntityException {
        if(!this.studentDAO.exists(session, ID))
            throw new NonExistentEntityException();

        Student student = this.studentDAO.loadStudentByORMID(session, ID);
        return student;
    }

    @Override
    public Student getStudentByEmail(PersistentSession session, String email) throws NonExistentEntityException, PersistentException {
        if(!this.studentDAO.exists(session, email))
            throw new NonExistentEntityException();

        Student student = this.studentDAO.loadStudentByEmail(session, email);
        return student;
    }

    @Override
    public boolean exists(PersistentSession session, int ID) throws PersistentException {
        return this.studentDAO.exists(session, ID);
    }

    @Override
    public boolean exists(PersistentSession session, String email) throws PersistentException {
        return this.studentDAO.exists(session, email);
    }

    @Override
    public boolean existsActive(PersistentSession session, String email) throws PersistentException {
        return this.studentDAO.existsActive(session, email);
    }
}
