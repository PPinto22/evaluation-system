package controller;

import exception.InvalidClaimsException;
import exception.NonExistentEntityException;
import io.jsonwebtoken.Claims;
import model.persistent.*;
import model.persistent.Class;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.orm.PersistentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.JwtService;
import service.ExamService;
import service.GroupService;
import service.UserService;
import wrapper.ErrorWrapper;
import wrapper.ExamWrapper;

import static controller.ErrorMessages.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "api/exams")
public class ExamController {

    ExamService examService;
    GroupService groupService;
    UserService userService;
    JwtService jwtService;

    public ExamController(ExamService examService, GroupService groupService, UserService userService, JwtService jwtService) {
        this.examService = examService;
        this.groupService = groupService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @RequestMapping(value = "/{examID:[\\d]+}", method = GET)
    public ResponseEntity<Object> getExam(@PathVariable int examID, HttpServletRequest request){
        try{
            User user = jwtService.getUser((Claims)request.getAttribute("claims"));
            Exam exam = examService.getExamByID(examID);
            Group group = exam.get_group();
            Class cl = group.get_class();
            switch (user.getClass().getSimpleName()){
                case "Teacher":
                    if(user.getID() == cl.get_teacher().getID())
                        return new ResponseEntity<Object>(new ExamWrapper(exam,false, false), OK);
                    else
                        return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);
                case "Student":
                    Student student = (Student)user;
                    if(!groupService.studentInGroup(student,group)) {
                            return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);
                    }

                    if(examService.examHasStarted(exam))
                            return new ResponseEntity<Object>(new ExamWrapper(exam,false,true), OK);
                    else
                        return new ResponseEntity<Object>(new ExamWrapper(exam, true, true), OK);
                default:
                    return null; // Nunca acontece
            }
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_EXAM), NOT_FOUND);
        }
    }


}
