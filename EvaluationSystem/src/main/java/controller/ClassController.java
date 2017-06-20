package controller;

import exception.NonExistentEntityException;
import model.Class;
import org.orm.PersistentException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ClassService;
import wrapper.ClassTeacherWrapper;
import wrapper.ErrorWrapper;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static controller.ErrorMessages.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "classes") // TODO - api/
public class ClassController {

    ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

// TODO
// PUT     /classes/{id}
// DELETE  /classes/{id}

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


}
