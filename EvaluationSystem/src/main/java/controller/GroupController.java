package controller;

import exception.ExistentEntityException;
import exception.InvalidClaimsException;
import exception.InvalidUserTypeException;
import exception.NonExistentEntityException;
import io.jsonwebtoken.Claims;
import model.*;
import model.Class;
import org.orm.PersistentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.JwtService;
import service.GroupService;
import service.StudentService;
import wrapper.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static controller.ErrorMessages.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "api/groups")
public class GroupController {

    JwtService jwtService;
    GroupService groupService;
    StudentService studentService;

    public GroupController(JwtService jwtService, GroupService groupService) {
        this.jwtService = jwtService;
        this.groupService = groupService;
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = GET)
    public ResponseEntity<Object> getGroup(@PathVariable int id){
        try {
            Group group = this.groupService.getGroupByID(id);
            return new ResponseEntity<Object>(new GroupClassWrapper(group), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{groupID:[\\d]+}/students", method = POST)
    // TODO - Notificacoes
    public ResponseEntity<Object> postStudents(@PathVariable int groupID, @RequestBody EmailWrapper[] studentEmails,
                                               HttpServletRequest request){
        try {
            User user = jwtService.getUser((Claims)request.getAttribute("claims"));
            Group group = groupService.getGroupByID(groupID);
            Class cl = group.get_class();

            if(!(cl.get_teacher().getID() == user.getID()))
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            List<GroupStudentPOSTWrapper> groupStudents = new ArrayList<>();
            for(EmailWrapper emailWrapper: studentEmails){
                String email = emailWrapper.getEmail();
                try {
                    GroupStudent groupStudent = this.groupService.addStudentToGroupByEmail(group, email);
                    groupStudents.add(new GroupStudentPOSTWrapper(groupStudent));
                } catch (InvalidUserTypeException e) {
                    groupStudents.add(new GroupStudentPOSTWrapper(email, USER_IS_TEACHER));
                } catch (ExistentEntityException e) {
                    groupStudents.add(new GroupStudentPOSTWrapper(email, STUDENT_IN_GROUP));
                }
            }
            return new ResponseEntity<Object>(groupStudents, OK);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{groupID:[\\d]+/students", method = GET)
    public ResponseEntity<Object> getStudents(@PathVariable int groupID){
        // TODO
        return null;
    }
}
