package controller;

import exception.ExistentEntityException;
import exception.InvalidClaimsException;
import exception.InvalidQuestionException;
import exception.NonExistentEntityException;
import io.jsonwebtoken.Claims;
import model.persistent.*;
import model.persistent.Class;
import org.orm.PersistentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.JwtService;
import service.ClassService;
import wrapper.*;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static controller.ErrorMessages.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api/classes")
public class ClassController {

    private JwtService jwtService;
    private ClassService classService;

    public ClassController(JwtService jwtService, ClassService classService){
        this.jwtService = jwtService;
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
    public ResponseEntity<Object> postGroup(@PathVariable int classID, @RequestBody Group group, HttpServletRequest request){
        try {
            User user = jwtService.getUser((Claims)request.getAttribute("claims"));
            Class cl = this.classService.getClassByID(classID);
            if(!user.equals(cl.get_teacher()))
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            classService.addGroupToClass(cl, group);
            return new ResponseEntity<Object>(new GroupClassWrapper(group), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_CLASS), NOT_FOUND);
        } catch (ExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(GROUP_EXISTS), NOT_ACCEPTABLE);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_AUTHENTICATION), UNAUTHORIZED);
        }
    }
    
    @RequestMapping(value = "/{classID:[\\d]+}/groups", method = GET)
    public ResponseEntity<Object> postGroup(@PathVariable int classID){
        try {
            Class cl = classService.getClassByID(classID);
            List<GroupWrapper> groups = new ArrayList<>();
            for(Group group: cl._groups.toArray()){
                groups.add(new GroupWrapper(group));
            }
            return new ResponseEntity<Object>(groups, OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_CLASS), NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{classID:[\\d]+}/questions", method = GET)
    public ResponseEntity<Object> getQuestions(@PathVariable int classID, HttpServletRequest request){
        try {
            User user = jwtService.getUser((Claims)request.getAttribute("claims"));
            Class cl = classService.getClassByID(classID);
            if(!user.equals(cl.get_teacher()))
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            List<Question> questions = classService.listClassQuestions(cl);
            List<QuestionWrapper> questionWrappers = new ArrayList<>();
            for(Question question: questions)
                questionWrappers.add(new QuestionWrapper(question, false));

            return new ResponseEntity<Object>(questionWrappers, OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_CLASS), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_AUTHENTICATION), UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/{classID:[\\d]+}/questions", method = POST)
    public ResponseEntity<Object> postQuestions(@PathVariable int classID,
                                                @RequestBody QuestionWrapper questionWrapper,
                                                HttpServletRequest request){
        try {
            User user = jwtService.getUser((Claims)request.getAttribute("claims"));
            Class cl = classService.getClassByID(classID);
            if(user.getID() != cl.get_teacher().getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);
            Question question = this.getQuestionFromWrapper(questionWrapper);
            question = this.classService.addQuestionToClass(cl, question);
            return new ResponseEntity<Object>(new QuestionWrapper(question, false), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_CLASS), NOT_FOUND);
        } catch (InvalidQuestionException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_QUESTION), NOT_ACCEPTABLE);
        } catch (ExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(QUESTION_EXISTS), NOT_ACCEPTABLE);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_AUTHENTICATION), UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/{classID:[\\d]+}/categories", method = GET)
    public ResponseEntity<Object> getCategories(@PathVariable int classID, HttpServletRequest request){
        try {
            User user = jwtService.getUser((Claims)request.getAttribute("claims"));
            Class cl = classService.getClassByID(classID);
            if(!user.equals(cl.get_teacher()))
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            Set<String> categories = classService.getClassCategories(cl);
            return new ResponseEntity<Object>(categories, OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_CLASS), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_AUTHENTICATION), UNAUTHORIZED);
        }
    }

    private Question getQuestionFromWrapper(QuestionWrapper questionWrapper) throws InvalidQuestionException {
        Question question = new Question();
        question.setText(questionWrapper.getText());
        question.setCategory(questionWrapper.getCategory());
        question.setDifficulty(questionWrapper.getDifficulty());
        List<AnswerWrapper> answerWrappers = questionWrapper.getAnswers();
        for(int i = 0; i<answerWrappers.size(); i++){
            AnswerWrapper answerWrapper = answerWrappers.get(i);
            Answer answer = new Answer();
            answer.setText(answerWrapper.getText());
            if(answerWrapper.isCorrect() == null)
                throw new InvalidQuestionException();
            answer.setCorrect(answerWrapper.isCorrect());
            answer.setOrder(i);
            question._answers.add(answer);
        }
        return question;
    }
}
