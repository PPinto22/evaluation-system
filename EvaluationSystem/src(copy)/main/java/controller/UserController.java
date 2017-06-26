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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.JwtService;
import service.ExamService;
import service.StudentService;
import service.TeacherService;
import service.UserService;
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

    public UserController(JwtService jwtService, UserService userService, TeacherService teacherService,
                          StudentService studentService, ExamService examService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.examService = examService;
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
}