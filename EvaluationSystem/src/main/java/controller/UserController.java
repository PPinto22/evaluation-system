package controller;

import model.*;
import org.orm.PersistentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import serializer.UserSerializationMode;
import serializer.UserSerializer;
import service.UserService;
import view.StudentDefaultView;
import view.TeacherDefaultView;
import view.UserView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserSerializer userSerializer;

    public UserController(UserService userService, UserSerializer userSerializer){
        this.userService = userService;
        this.userSerializer = userSerializer;
    }

    // TODO - Passar isto para outro controlador, e gerar token (??)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Void> login(@RequestBody User user){
        try {
            if(userService.authenticate(user)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (PersistentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public ResponseEntity<UserView> postUser(@RequestBody Student student){
        try {
            userService.addStudent(student);
            return new ResponseEntity<>(new UserView(student), HttpStatus.ACCEPTED);
        } catch (PersistentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new UserView(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.POST)
    public ResponseEntity<UserView> postTeacher(@RequestBody Teacher teacher){
        try {
            userService.addTeacher(teacher);
            return new ResponseEntity<>(new UserView(teacher), HttpStatus.ACCEPTED);
        } catch (PersistentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new UserView(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ResponseEntity<List<StudentDefaultView>> getStudents(){
        List<StudentDefaultView> views = new ArrayList<>();
        try{
            Student[] students = userService.getStudents();
            for(Student student: students){
                views.add(new StudentDefaultView(student));
            }
            return new ResponseEntity<>(views, HttpStatus.OK);
        }catch (PersistentException e){
            return new ResponseEntity<>(views, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public ResponseEntity<List<TeacherDefaultView>> getTeachers(){
        List<TeacherDefaultView> views = new ArrayList<>();
        try {
            Teacher[] teachers = userService.getTeachers();
            for (Teacher teacher : teachers) {
                views.add(new TeacherDefaultView(teacher));
            }
            return new ResponseEntity<>(views, HttpStatus.OK);
        }catch (PersistentException e){
            return new ResponseEntity<>(views, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}