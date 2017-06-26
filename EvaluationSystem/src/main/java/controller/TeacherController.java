package controller;

import exception.ExistentEntityException;
import exception.InvalidClaimsException;
import exception.MissingInformationException;
import exception.NonExistentEntityException;
import io.jsonwebtoken.Claims;
import model.persistent.Class;
import model.persistent.Teacher;
import model.persistent.User;
import org.orm.PersistentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.JwtService;
import service.ClassService;
import service.TeacherService;
import wrapper.ClassWrapper;
import wrapper.ErrorWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static controller.ErrorMessages.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "api/teachers")
public class TeacherController {

    JwtService jwtService;
    TeacherService teacherService;
    ClassService classService;

    public TeacherController(JwtService jwtService, TeacherService teacherService, ClassService classService) {
        this.jwtService = jwtService;
        this.teacherService = teacherService;
        this.classService = classService;
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
}
