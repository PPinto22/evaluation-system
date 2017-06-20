package controller;

import exception.ExistentEntityException;
import exception.InvalidClaimsException;
import exception.NonExistentEntityException;
import io.jsonwebtoken.Claims;
import model.Class;
import model.Group;
import model.User;
import org.orm.PersistentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.JwtService;
import service.ClassService;
import wrapper.ClassTeacherWrapper;
import wrapper.ErrorWrapper;
import wrapper.GroupClassWrapper;

import javax.servlet.http.HttpServletRequest;

import static controller.ErrorMessages.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api/classes")
public class ClassController {

    JwtService jwtService;
    ClassService classService;

    public ClassController(JwtService jwtService, ClassService classService){
        this.jwtService = jwtService;
        this.classService = classService;
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = GET)
    public ResponseEntity<Object> getClass(@PathVariable int id){
        Class cl = null;
        try {
            cl = classService.getClassByID(id);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_CLASS), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(new ClassTeacherWrapper(cl),OK);
    }

    // TODO - Testar e Documentar no readme
    @RequestMapping(value = "/{classID:[\\d]+}/groups", method = POST)
    public ResponseEntity<Object> postGroup(@PathVariable int classID, @RequestBody Group group, HttpServletRequest request){
        try {
            User user = jwtService.getUser((Claims)request.getAttribute("claims"));
            Class cl = this.classService.getClassByID(classID);
            if(!user.equals(cl.get_teacher()))
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            classService.addGroupToClass(cl, group);
            return new ResponseEntity<Object>(new GroupClassWrapper(group), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_CLASS), NOT_FOUND);
        } catch (ExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(GROUP_EXISTS), NOT_ACCEPTABLE);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_AUTHENTICATION), UNAUTHORIZED);
        }
    }

}
