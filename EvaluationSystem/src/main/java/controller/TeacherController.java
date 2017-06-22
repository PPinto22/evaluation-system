package controller;

import exception.ExistentEntityException;
import exception.InvalidUserTypeException;
import exception.MissingInformationException;
import exception.NonExistentEntityException;
import model.Class;
import model.Teacher;
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

import java.util.ArrayList;
import java.util.List;

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

    // TODO - verificar que emissor e' o professor
    @RequestMapping(value = "/{teacherID:[\\d]+}/classes", method = POST)
    public ResponseEntity<Object> postClass(@PathVariable int teacherID, @RequestBody Class cl){
        try {
            Teacher teacher = teacherService.getTeacherByID(teacherID);
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
        }
    }

    @RequestMapping(value = "/{teacherID:[\\d]+}/classes", method = GET)
    public ResponseEntity<Object> getClasses(@PathVariable int teacherID){
        try {
            Teacher teacher = teacherService.getTeacherByID(teacherID);
            List<Class> classes = teacherService.getClasses(teacher);
            List<ClassWrapper> classWrappers = new ArrayList<>();
            for(Class cl: classes)
                classWrappers.add(new ClassWrapper(cl));
            return new ResponseEntity<Object>(classWrappers, OK);
        } catch (PersistentException e){
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_TEACHER), NOT_FOUND);
        }
    }
}
