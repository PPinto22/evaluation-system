package controller;

import model.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import serializer.UserSerializationMode;
import serializer.UserSerializer;
import service.UserService;

import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping("/users")
// TODO - Não definitivo; para experiencias.
public class UserController {

    private UserService userService;
    private UserSerializer userSerializer;

    public UserController(UserService userService, UserSerializer userSerializer){
        this.userService = userService;
        this.userSerializer = userSerializer;
    }

    @RequestMapping(value = "/teachers/add", method = RequestMethod.GET)
    public void addUser(){
        Teacher teacher = new Teacher();
        teacher.setEmail("john@gmail.com");
        teacher.setFirstName("Teacher");
        teacher.setLastName("1");
        teacher.setPassword("password");
        teacher.setDeleted(false);
        teacher.setRegistered(true);
        teacher.setRegistrationCode(UUID.randomUUID().toString());

        model.Class cl = new model.Class();
        cl.setAbbreviation("AA");
        cl.setName("Arquiteturas Aplicacionais");
        cl.set_teacher(teacher);

        userService.addUser(teacher);
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public Student[] getStudents(){
        return userService.getStudents();
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public String getTeachers(){
        Teacher[] teachers = userService.getTeachers();
        return userSerializer.serialize(Arrays.asList(teachers), UserSerializationMode.DEFAULT);
    }
}