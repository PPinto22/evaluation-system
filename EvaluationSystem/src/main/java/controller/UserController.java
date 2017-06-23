package controller;

import exception.InvalidClaimsException;
import exception.InvalidUserTypeException;
import exception.NonExistentEntityException;
import io.jsonwebtoken.Claims;
import model.*;
import model.Class;
import org.orm.PersistentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import security.JwtService;
import service.TeacherService;
import service.UserService;
import wrapper.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static controller.ErrorMessages.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService userService;
    private TeacherService teacherService;
    private JwtService jwtService;

    public UserController(UserService userService, TeacherService teacherService, JwtService jwtService) {
        this.userService = userService;
        this.teacherService = teacherService;
        this.jwtService = jwtService;
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


    // FIXME - Testes...
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ResponseEntity<List<StudentGroupsWrapper>> getStudents(HttpServletRequest request){
        // Exemplo de como retirar o user a partir do token
        try {
            User user = jwtService.getUser((Claims)request.getAttribute("claims"));
            System.out.println(user.toString());
        } catch (InvalidClaimsException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        List<StudentGroupsWrapper> views = new ArrayList<>();
        try{
            Student[] students = userService.getStudents();
            for(Student student: students){
                views.add(new StudentGroupsWrapper(student));
            }
            return new ResponseEntity<>(views, HttpStatus.OK);
        }catch (PersistentException e){
            return new ResponseEntity<>(views, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public ResponseEntity<List<TeacherClassesWrapper>> getTeachers(){
        List<TeacherClassesWrapper> views = new ArrayList<>();
        try {
            Teacher[] teachers = userService.getTeachers();
            for (Teacher teacher : teachers) {
                views.add(new TeacherClassesWrapper(teacher));
            }
            return new ResponseEntity<>(views, HttpStatus.OK);
        }catch (PersistentException e){
            return new ResponseEntity<>(views, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}