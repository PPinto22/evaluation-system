package controller;

import dao.ClassesPersistentManager;
import exception.*;
import io.jsonwebtoken.Claims;
import model.*;
import model.Class;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import security.JwtService;
import service.*;
import wrapper.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static controller.ErrorMessages.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    private JwtService jwtService;
    private UserService userService;
    private TeacherService teacherService;
    private StudentService studentService;
    private ExamService examService;
    private GroupService groupService;
    private SubmissionService submissionService;

    public UserController(JwtService jwtService, UserService userService,
                          TeacherService teacherService, StudentService studentService,
                          ExamService examService, GroupService groupService,
                          SubmissionService submissionService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.examService = examService;
        this.groupService = groupService;
        this.submissionService = submissionService;
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = GET)
    public ResponseEntity<Object> getUser(@PathVariable int id) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = userService.getUserByID(session, id);
            return new ResponseEntity<Object>(new UserWrapper(user), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_USER), NOT_FOUND);
        }

    }

    @RequestMapping(value = "/{id:[\\d]+}", method = PUT)
    public ResponseEntity<Object> updateUser(@PathVariable int id,
                                             @RequestBody User userWrapper,
                                             HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User clientUser = jwtService.getUser(session, (Claims)request.getAttribute("claims"));
            User user = userService.getUserByID(session, id);
            if(clientUser.getID() != user.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            user = userService.update(user, userWrapper.getFirstName(), userWrapper.getLastName(), userWrapper.getPassword());
            return new ResponseEntity<Object>(new UserWrapper(user), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_USER), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable int id, HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User clientUser = jwtService.getUser(session, (Claims)request.getAttribute("claims"));
            User user = userService.getUserByID(session, id);
            if(clientUser.getID() != user.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            userService.delete(session, user);
            return new ResponseEntity<Object>(new Object(), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_USER), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{id:[\\d]+}/notifications", method = GET)
    public ResponseEntity<Object> getNotifications(@PathVariable int id, HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User clientUser = jwtService.getUser(session, (Claims)request.getAttribute("claims"));
            User user = userService.getUserByID(session, id);
            if(clientUser.getID() != user.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            List<NotificationWrapper> notifications = new ArrayList<>();
            for(Notification notification: user._notifications.toArray()){
                switch (notification.getClass().getSimpleName()){
                    case "GroupInvitation":
                        notifications.add(new GroupInvitationWrapper((GroupInvitation)notification));
                }
            }
            return new ResponseEntity<Object>(notifications, OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_USER), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{id:[\\d]+}/groups", method = GET)
    public ResponseEntity<Object> getGroups(@PathVariable int id, HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User clientUser = jwtService.getUser(session, (Claims)request.getAttribute("claims"));
            User user = userService.getUserByID(session, id);
            if(clientUser.getID() != user.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            Set<GroupWrapper> groups = new TreeSet<>();
            switch (user.getClass().getSimpleName()){
                case "Teacher":
                    Teacher teacher = (Teacher)user;
                    for(Group group: teacherService.getGroups(teacher))
                        groups.add(new GroupClassWrapper(group));
                    break;
                case "Student":
                    Student student = (Student)user;
                    for(Group group: studentService.getStudentGroups(student))
                        groups.add(new GroupClassWrapper(group));
                    break;
            }
            return new ResponseEntity<Object>(groups, OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_USER), NOT_FOUND);
        } catch (InvalidClaimsException e) {
        }
        return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
    }

    @RequestMapping(value = "/{studentID:[\\d]+}/groups/{groupID:[\\d]+}", method = DELETE)
    public ResponseEntity<Object> leaveGroup(@PathVariable int studentID, @PathVariable int groupID, HttpServletRequest request) throws PersistentException{
        Student student = null;
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User clientUser = jwtService.getUser(session, (Claims)request.getAttribute("claims"));
            student = studentService.getStudentByID(session, studentID);
            if(clientUser.getID() != student.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);
            Group group = groupService.getGroupByID(session, groupID);
            if(!groupService.studentInGroup(student,group))
                return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);

            studentService.leaveGroup(session, student, group);
            return new ResponseEntity<Object>(new Object(), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            if(student == null)
                return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_USER), NOT_FOUND);
            else
                return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } catch (StudentNotInGroupException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{userID:[\\d]+}/classes", method = GET)
    public ResponseEntity<Object> getClasses(@PathVariable int userID, HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User clientUser = jwtService.getUser(session, (Claims)request.getAttribute("claims"));
            User user = userService.getUserByID(session, userID);
            if(user.getID() != clientUser.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            Set<ClassWrapper> classes = new TreeSet<>();
            switch (user.getClass().getSimpleName()){
                case "Teacher":
                    Teacher teacher = (Teacher)user;
                    for(Class cl: teacherService.getClasses(teacher))
                        classes.add(new ClassWrapper(cl));
                    break;
                case "Student":
                    Student student = (Student)user;
                    for(Class cl: studentService.getStudentClasses(student))
                        classes.add(new ClassTeacherWrapper(cl));
                    break;
            }
            return new ResponseEntity<Object>(classes, OK);
        } catch (PersistentException e){
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_USER), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{teacherID:[\\d]+}/classes", method = POST)
    public ResponseEntity<Object> postClass(@PathVariable int teacherID,
                                            @RequestBody Class cl,
                                            HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User clientUser = jwtService.getUser(session, (Claims)request.getAttribute("claims"));
            Teacher teacher = teacherService.getTeacherByID(session, teacherID);
            if(clientUser.getID() != teacher.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            teacherService.addClassToTeacher(session, teacher, cl);
            return new ResponseEntity<Object>(new ClassWrapper(cl), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_TEACHER), NOT_FOUND);
        } catch (MissingInformationException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(MISSING_INFORMATION), NOT_ACCEPTABLE);
        } catch (ExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(CLASS_EXISTS), NOT_ACCEPTABLE);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{userID:[\\d]+}/exams", method = GET)
    public ResponseEntity<Object> getExams(@PathVariable int userID,
                                           @RequestParam(required = false) String ongoing,
                                           HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User clientUser = jwtService.getUser(session, (Claims)request.getAttribute("claims"));
            User user = userService.getUserByID(session, userID);
            if(user.getID() != clientUser.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            if(ongoing != null){
                return new ResponseEntity<Object>(wrapExamList(examService.getOngoingExamsByUser(user), true), OK);
            }
            ExamsWrapper examsWrapper = new ExamsWrapper(examService.getExamsByUser(user), true);
            return new ResponseEntity<Object>(examsWrapper, OK);
        } catch (PersistentException e){
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_USER), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } finally {
            session.close();
        }
    }

    private List<ExamWrapper> wrapExamList(Collection<Exam> exams, boolean showClass){
        List<ExamWrapper> examWrappers = new ArrayList<>();
        for(Exam exam: exams){
            examWrappers.add(
                    showClass ?
                            new ExamClassWrapper(exam,true,true) :
                            new ExamWrapper(exam,true,true)
            );
        }
        return examWrappers;
    }

    @RequestMapping(value = "/{studentID:[\\d]+}/scores", method = GET)
    public ResponseEntity<Object> getScores(@PathVariable int studentID,
                                            @RequestParam Map<String,String> requestParams,
                                            HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User clientUser = jwtService.getUser(session, (Claims)request.getAttribute("claims"));
            Student student = studentService.getStudentByID(session, studentID);
            if(student.getID() != clientUser.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            try {
                Exam exam = getExamFromRequests(session, requestParams);
                Score score = studentService.getStudentScoreByExam(session, student, exam);
                return new ResponseEntity<Object>(new ScoreWrapper(score), OK);
            } catch (NonExistentEntityException | StudentNotInGroupException | InvalidExamException e){
                return new ResponseEntity<Object>(new ErrorWrapper(INVALID_EXAM), NOT_ACCEPTABLE);
            } catch (NoSuchParamException e) { // No such exam; try group
                try {
                    Group group = getGroupFromRequests(session, requestParams);
                    Map<Exam, Score> examScoreMap = studentService.getStudentScoresByGroup(session, student, group);
                    return new ResponseEntity<Object>(new ExamsScoresWrapper(examScoreMap), OK);
                } catch (NonExistentEntityException | StudentNotInGroupException e1) {
                    return new ResponseEntity<Object>(new ErrorWrapper(INVALID_GROUP), NOT_ACCEPTABLE);
                } catch (NoSuchParamException e1) { // No such group; list all
                    Map<Group, Map<Exam, Score>> groupExamMap = studentService.getStudentScores(session, student);
                    return new ResponseEntity<Object>(new GroupsExamsScoresWrapper(groupExamMap), OK);
                }
            }
        } catch (PersistentException e){
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_STUDENT), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{studentID:[\\d]+}/submissions", method = GET)
    public ResponseEntity<Object> getSubmissions(@PathVariable int studentID,
                                                 @RequestParam Map<String,String> requestParams,
                                                 HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User clientUser = jwtService.getUser(session, (Claims)request.getAttribute("claims"));
            Student student  = studentService.getStudentByID(session, studentID);
            if(student.getID() != clientUser.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            List<SubmissionExamWrapper> submissionWrappers = new ArrayList<>();
            try {
                Exam exam = getExamFromRequests(session, requestParams);
                if (!groupService.studentInGroup(student,exam.get_group()))
                    return new ResponseEntity<Object>(new ErrorWrapper(INVALID_EXAM), NOT_ACCEPTABLE);

                try{
                    Submission submission = submissionService.getSubmissionByStudentAndExam(session, student,exam);
                    submissionWrappers.add(new SubmissionExamWrapper(submission, true, true));
                } catch (NonExistentEntityException e){
                }
                return new ResponseEntity<Object>(submissionWrappers, OK);
            } catch (NonExistentEntityException e){
                return new ResponseEntity<Object>(new ErrorWrapper(INVALID_EXAM), NOT_ACCEPTABLE);
            } catch (NoSuchParamException e) { // No such exam; try group
                try {
                    Group group = getGroupFromRequests(session, requestParams);
                    if(!groupService.studentInGroup(student,group))
                        return new ResponseEntity<Object>(new ErrorWrapper(INVALID_GROUP), NOT_ACCEPTABLE);

                    List<Exam> exams = group.getExams();
                    for(Exam exam: exams){
                        try{
                            Submission submission = submissionService.getSubmissionByStudentAndExam(session, student,exam);
                            submissionWrappers.add(new SubmissionExamWrapper(submission, true, true));
                        }catch (NonExistentEntityException exc){}
                    }
                    return new ResponseEntity<Object>(submissionWrappers, OK);
                } catch (NonExistentEntityException e1){
                    return new ResponseEntity<Object>(new ErrorWrapper(INVALID_GROUP), NOT_ACCEPTABLE);
                } catch (NoSuchParamException e1) { // No such group; list all submissions
                    List<Submission> submissions = Arrays.asList(student._submissions.toArray());
                    for(Submission submission: submissions){
                        submissionWrappers.add(new SubmissionExamWrapper(submission, true, true));
                    }
                    return new ResponseEntity<Object>(submissionWrappers, OK);
                }
            }
        } catch (PersistentException e){
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_STUDENT), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } finally {
            session.close();
        }
    }

    private Group getGroupFromRequests(PersistentSession session, Map<String, String> requestParams)
            throws PersistentException, NonExistentEntityException, NoSuchParamException {
        Integer groupID = null;
        Group group = null;
        if(requestParams.containsKey("group")) {
            String groupString = requestParams.get("group");
            try {
                groupID = Integer.parseInt(groupString);
            }
            catch (Exception e){}
            group = groupService.getGroupByID(session, groupID);
            return group;
        }
        else
            throw new NoSuchParamException();
    }

    private Exam getExamFromRequests(PersistentSession session, Map<String, String> requestParams)
            throws PersistentException, NonExistentEntityException, NoSuchParamException {
        Integer examID = null;
        Exam exam = null;
        if(requestParams.containsKey("exam")) {
            String examString = requestParams.get("exam");
            try {
                examID = Integer.parseInt(examString);
            }
            catch (Exception e){}
            exam = examService.getExamByID(session, examID);
            return exam;
        }
        else
            throw new NoSuchParamException();
    }
}
