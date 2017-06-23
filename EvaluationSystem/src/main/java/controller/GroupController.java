package controller;

import exception.ExistentEntityException;
import exception.InvalidClaimsException;
import exception.InvalidUserTypeException;
import exception.NonExistentEntityException;
import io.jsonwebtoken.Claims;
import model.Class;
import model.*;
import org.orm.PersistentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.JwtService;
import service.GroupService;
import service.StudentService;
import wrapper.ErrorWrapper;
import wrapper.GroupClassWrapper;
import wrapper.GroupStudentPOSTWrapper;
import wrapper.GroupStudentWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    public GroupController(JwtService jwtService, GroupService groupService, StudentService studentService) {
        this.jwtService = jwtService;
        this.groupService = groupService;
        this.studentService = studentService;
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
    public ResponseEntity<Object> postStudents(@PathVariable int groupID, @RequestBody String[] studentEmails,
                                               HttpServletRequest request){
        try {
            User user = jwtService.getUser((Claims)request.getAttribute("claims"));
            Group group = groupService.getGroupByID(groupID);
            Class cl = group.get_class();

            if(!(cl.get_teacher().getID() == user.getID()))
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            List<GroupStudentPOSTWrapper> groupStudents = new ArrayList<>();
            for(String email: studentEmails){
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

    @RequestMapping(value = "/{groupID:[\\d]+}/students", method = GET)
    public ResponseEntity<Object> getStudents(@PathVariable int groupID){
        try {
            Group group = groupService.getGroupByID(groupID);
            List<GroupStudentWrapper> groupStudents = new ArrayList<>();
            for(GroupStudent groupStudent: this.groupService.getGroupStudents(group)){
                groupStudents.add(new GroupStudentWrapper(groupStudent));
            }
            return new ResponseEntity<Object>(groupStudents, OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{groupID:[\\d]+}/students/{studentID:[\\d]+}")
    public ResponseEntity<Object> removeStudent(@PathVariable int groupID, @PathVariable int studentID){
        Group group = null;
        try {
            group = groupService.getGroupByID(groupID);
            Student student = studentService.getStudentByID(studentID);
            groupService.removeStudentFromGroup(group,student);
            return new ResponseEntity<Object>(OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            if(group == null)
                return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
            else
                return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_STUDENT), NOT_FOUND);
        }
    }
}
