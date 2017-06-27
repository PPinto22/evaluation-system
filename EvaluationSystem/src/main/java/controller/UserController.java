package controller;

import exception.ExistentEntityException;
import exception.InvalidClaimsException;
import exception.MissingInformationException;
import exception.NonExistentEntityException;
import io.jsonwebtoken.Claims;
import model.persistent.*;
import model.persistent.Class;
import org.orm.PersistentException;
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
    public ResponseEntity<Object> getUser(@PathVariable int id){
        try {
            User user = userService.getUserByID(id);
            return new ResponseEntity<Object>(new UserWrapper(user), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_USER), NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id:[\\d]+}/notifications", method = GET)
    public ResponseEntity<Object> getNotifications(@PathVariable int id, HttpServletRequest request){
        try {
            User clientUser = jwtService.getUser((Claims)request.getAttribute("claims"));
            User user = userService.getUserByID(id);
            if(clientUser.getID() != user.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

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
        }
    }

    @RequestMapping(value = "/{id:[\\d]+}/groups", method = GET)
    public ResponseEntity<Object> getGroups(@PathVariable int id, HttpServletRequest request){
        try {
            User clientUser = jwtService.getUser((Claims)request.getAttribute("claims"));
            User user = userService.getUserByID(id);
            if(clientUser.getID() != user.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            Set<GroupWrapper> groups = new HashSet<>();
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

    @RequestMapping(value = "/{userID:[\\d]+}/classes", method = GET)
    public ResponseEntity<Object> getClasses(@PathVariable int userID, HttpServletRequest request){
        try {
            User clientUser = jwtService.getUser((Claims)request.getAttribute("claims"));
            User user = userService.getUserByID(userID);
            if(user.getID() != clientUser.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            Set<ClassWrapper> classes = new HashSet<>();
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
        }
    }

    @RequestMapping(value = "/{teacherID:[\\d]+}/classes", method = POST)
    public ResponseEntity<Object> postClass(@PathVariable int teacherID,
                                            @RequestBody Class cl,
                                            HttpServletRequest request){
        try {
            User clientUser = jwtService.getUser((Claims)request.getAttribute("claims"));
            Teacher teacher = teacherService.getTeacherByID(teacherID);
            if(clientUser.getID() != teacher.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            teacherService.addClassToTeacher(teacher, cl);
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
        }
    }

    @RequestMapping(value = "/{userID:[\\d]+}/exams", method = GET)
    public ResponseEntity<Object> getExams(@PathVariable int userID, HttpServletRequest request){
        try {
            User clientUser = jwtService.getUser((Claims)request.getAttribute("claims"));
            User user = userService.getUserByID(userID);
            if(user.getID() != clientUser.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            ExamsWrapper examsWrapper = new ExamsWrapper(examService.getExamsByUser(user), true);
            return new ResponseEntity<Object>(examsWrapper, OK);
        } catch (PersistentException e){
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_USER), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/{studentID:[\\d]+}/submissions", method = GET)
    public ResponseEntity<Object> getSubmissions(@PathVariable int studentID,
                                                 @RequestParam Map<String,String> requestParams,
                                                 HttpServletRequest request){
        try {
            User clientUser = jwtService.getUser((Claims)request.getAttribute("claims"));
            Student student  = studentService.getStudentByID(studentID);
            if(student.getID() != clientUser.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            Exam exam;
            try {
                exam = getExamFromRequests(requestParams);
            }catch (NonExistentEntityException e){
                return new ResponseEntity<Object>(new ErrorWrapper(INVALID_EXAM), NOT_ACCEPTABLE);
            }
            Group group;
            try {
                group = getGroupFromRequests(requestParams);
            }catch (NonExistentEntityException e){
                return new ResponseEntity<Object>(new ErrorWrapper(INVALID_GROUP), NOT_ACCEPTABLE);
            }

            List<SubmissionExamWrapper> submissionWrappers = new ArrayList<>();
            if(exam != null){
                if (!groupService.studentInGroup(student,exam.get_group()))
                    return new ResponseEntity<Object>(new ErrorWrapper(INVALID_EXAM), NOT_ACCEPTABLE);

                try{
                    Submission submission = submissionService.getSubmissionByStudentAndExam(student,exam);
                    submissionWrappers.add(new SubmissionExamWrapper(submission, true, true));
                } catch (NonExistentEntityException e){
                }
                return new ResponseEntity<Object>(submissionWrappers, OK);
            }else if(group != null){
                if(!groupService.studentInGroup(student,group))
                    return new ResponseEntity<Object>(new ErrorWrapper(INVALID_GROUP), NOT_ACCEPTABLE);

                List<Exam> exams = Arrays.asList(group._exams.toArray());
                for(Exam e: exams){
                    try{
                        Submission submission = submissionService.getSubmissionByStudentAndExam(student,e);
                        submissionWrappers.add(new SubmissionExamWrapper(submission, true, true));
                    }catch (NonExistentEntityException exc){}
                }
                return new ResponseEntity<Object>(submissionWrappers, OK);
            }else{
                List<Submission> submissions = Arrays.asList(student._submissions.toArray());
                for(Submission submission: submissions){
                    submissionWrappers.add(new SubmissionExamWrapper(submission, true, true));
                }
                return new ResponseEntity<Object>(submissionWrappers, OK);
            }
        } catch (PersistentException e){
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_STUDENT), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        }
    }

    private Group getGroupFromRequests(Map<String, String> requestParams) throws PersistentException, NonExistentEntityException {
        Integer groupID = null;
        Group group = null;
        if(requestParams.containsKey("group")) {
            String groupString = requestParams.get("group");
            try {
                groupID = Integer.parseInt(groupString);
            }
            catch (Exception e){}
            group = groupService.getGroupByID(groupID);
        }
        return group;
    }

    private Exam getExamFromRequests(Map<String, String> requestParams) throws PersistentException, NonExistentEntityException {
        Integer examID = null;
        Exam exam = null;
        if(requestParams.containsKey("exam")) {
            String examString = requestParams.get("exam");
            try {
                examID = Integer.parseInt(examString);
            }
            catch (Exception e){}
            exam = examService.getExamByID(examID);
        }
        return exam;
    }
}