package controller;

import exception.InvalidClaimsException;
import exception.NonExistentEntityException;
import io.jsonwebtoken.Claims;
import model.Question;
import model.User;
import org.orm.PersistentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.JwtService;
import service.QuestionService;
import wrapper.*;

import javax.servlet.http.HttpServletRequest;

import static controller.ErrorMessages.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("api/questions")
public class QuestionController {

    private QuestionService questionService;
    private JwtService jwtService;

    public QuestionController(QuestionService questionService, JwtService jwtService) {
        this.questionService = questionService;
        this.jwtService = jwtService;
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = GET)
    public ResponseEntity<Object> getQuestion(@PathVariable int id, HttpServletRequest request){
        try {
            User clientUser = jwtService.getUser((Claims)request.getAttribute("claims"));
            Question question = questionService.getQuestionByID(id);
            if(clientUser.getID() != question.get_class().get_teacher().getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            return new ResponseEntity<Object>(new QuestionWrapper(question,false), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_QUESTION), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        }
    }
}
