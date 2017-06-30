package service;

import dao.GroupStudentDAO;
import dao.NotificationDAO;
import dao.StudentDAO;
import exception.*;
import model.*;
import model.Class;
import org.orm.PersistentException;
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
    public void delete(Student student) throws PersistentException {
        if(inAGroup(student)){
            for(GroupStudent groupStudent: student._groups.toArray()){
                try {
                    leaveGroup(student, groupStudent.get_group());
                } catch (StudentNotInGroupException e) {}
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
    public void leaveGroup(Student student, Group group) throws PersistentException, StudentNotInGroupException {
        if(!groupStudentDAO.exists(group.getID(), student.getID()))
            throw new StudentNotInGroupException();
        GroupStudent groupStudent = groupStudentDAO.loadGroupStudentByGroupAndStudent(group.getID(), student.getID());
        groupStudent.setAccepted(false);
        groupStudentDAO.save(groupStudent);

        try{
            GroupInvitation groupInvitation = notificationService.getGroupInvitation(group, student);
            notificationService.removeGroupInvitation(groupInvitation);
        } catch (NonExistentEntityException e) {}
    }

    @Override
    public Map<Group, Map<Exam, Score>> getStudentScores(Student student) throws PersistentException {
        Map<Group, Map<Exam, Score>> groupMap = new TreeMap<>();
        for(Group group: getStudentGroups(student)){
            Map<Exam, Score> examMap = new TreeMap<>();
            for(Exam exam: group.getExams()){
                if(examService.examHasFinished(exam)) {
                    try {
                        Submission submission = submissionService.getSubmissionByStudentAndExam(student, exam);
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
    public Map<Exam, Score> getStudentScoresByGroup(Student student, Group group) throws StudentNotInGroupException, PersistentException {
        if(!groupService.studentInGroup(student,group))
            throw new StudentNotInGroupException();

        Map<Exam, Score> scoreMap = new TreeMap<>();
        for(Exam exam: group.getExams()){
            if(examService.examHasFinished(exam)) {
                try {
                    Submission submission = submissionService.getSubmissionByStudentAndExam(student, exam);
                    scoreMap.put(exam, new Score(submission));
                } catch (NonExistentEntityException e) {
                    scoreMap.put(exam, new Score());
                }
            }
        }

        return scoreMap;
    }

    @Override
    public Score getStudentScoreByExam(Student student, Exam exam) throws PersistentException, StudentNotInGroupException, InvalidExamException {
        if(!examService.examHasFinished(exam))
            throw new InvalidExamException();
        if(!groupService.studentInGroup(student,exam.get_group()))
            throw new StudentNotInGroupException();
        Score score = null;
        try {
            Submission submission = submissionService.getSubmissionByStudentAndExam(student,exam);
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
    public Student addStudent(Student student, boolean register) throws MissingInformationException, PersistentException, ExistentEntityException {
        Student addedStudent = null;
        try {
            addedStudent = (Student) userService.signup(student, "student", register);
        } catch (InvalidUserTypeException e) {
            e.printStackTrace();
        }
        return addedStudent;
    }

    @Override
    public Student getStudentByID(int ID) throws PersistentException, NonExistentEntityException {
        if(!this.studentDAO.exists(ID))
            throw new NonExistentEntityException();

        return this.studentDAO.loadStudentByORMID(ID);
    }

    @Override
    public Student getStudentByEmail(String email) throws NonExistentEntityException, PersistentException {
        if(!this.studentDAO.exists(email))
            throw new NonExistentEntityException();

        return this.studentDAO.loadStudentByEmail(email);
    }


    /*
        Situacoes regulares:
        - Utilizador existe e esta' registado OU
          Utilizador existe mas nao esta' registado (variavel registered = 0)
            -> Retorna utilizador existente
        - Utilizador nao existe OU
          Utilizador existe mas esta' marcado como apagado (variavel deleted = 1)
            -> Cria novo utilizador (com variavel registered = 0) e retorna-o
        Excepcoes:
        - Utilizador existe mas e' um professor
            -> InvalidUserTypeException
     */
    @Override
    public Student getOrCreateStudentByEmail(String email) {
        return null;
    }

    @Override
    public boolean exists(int ID) throws PersistentException {
        return this.studentDAO.exists(ID);
    }

    @Override
    public boolean exists(String email) throws PersistentException {
        return this.studentDAO.exists(email);
    }

    @Override
    public boolean existsActive(String email) throws PersistentException {
        return this.studentDAO.existsActive(email);
    }
}
