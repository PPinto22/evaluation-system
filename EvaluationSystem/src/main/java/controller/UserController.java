package controller;

import exception.InvalidClaimsException;
import io.jsonwebtoken.Claims;
import model.Student;
import model.Teacher;
import model.User;
import org.orm.PersistentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import security.JwtService;
import service.UserService;
import wrapper.StudentGroupsWrapper;
import wrapper.TeacherClassesWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    private JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

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