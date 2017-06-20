package controller;

import exception.InvalidClaimsException;
import exception.InvalidUserTypeException;
import exception.NonExistentEntityException;
import io.jsonwebtoken.Claims;
import model.Student;
import model.Teacher;
import model.User;
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
import wrapper.ClassWrapper;
import wrapper.ErrorWrapper;
import wrapper.StudentGroupsWrapper;
import wrapper.TeacherClassesWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static controller.ErrorMessages.*;

@RestController
@RequestMapping("users") //TODO api/
public class UserController {

    private UserService userService;
    private TeacherService teacherService;
    private JwtService jwtService;

    public UserController(UserService userService, TeacherService teacherService, JwtService jwtService) {
        this.userService = userService;
        this.teacherService = teacherService;
        this.jwtService = jwtService;
    }






    // Testes...
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    // FIXME: Metodo para testes
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
    // FIXME: Metodo para testes
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