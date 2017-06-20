package controller;

import exception.NonExistentEntityException;
import model.Class;
import model.Group;
import org.orm.PersistentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ClassService;
import wrapper.ClassTeacherWrapper;
import wrapper.ErrorWrapper;

import static controller.ErrorMessages.INTERNAL_ERROR;
import static controller.ErrorMessages.NO_SUCH_CLASS;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api/classes")
public class ClassController {

    ClassService classService;

    public ClassController(ClassService classService) {
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

    @RequestMapping(value = "/{classID:[\\d]+}/groups", method = POST)
    public ResponseEntity<Object> postGroup(@PathVariable int classID, @RequestBody Group group){
        try {
            Class cl = this.classService.getClassByID(classID);
            classService.addGroupToClass(cl, group);
        } catch (PersistentException e) {
            e.printStackTrace();
        } catch (NonExistentEntityException e) {
            e.printStackTrace();
        }
        return null; //TODO
    }

}
