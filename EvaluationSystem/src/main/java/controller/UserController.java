package controller;

import model.*;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserController(){

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public void addUser(){
        Student student = new Student();
        student.setEmail("john@gmail.com");
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setPassword("Pa$$word");
        student.setDeleted(false);
        student.setRegistered(true);
        student.setRegistrationCode(UUID.randomUUID().toString());
        try {
            StudentDAO dao = new StudentDAOImpl();
            dao.save(student);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<User[]> getUsers(){
        try {
            UserCriteria criteria = new UserCriteria();
            UserDAO dao = new UserDAOImpl();
            return new ResponseEntity<User[]>(dao.listUserByCriteria(criteria), HttpStatus.OK);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<User[]>(new User[0], HttpStatus.NO_CONTENT);
    }


}