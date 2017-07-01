package controller;

import dao.ClassesPersistentManager;
import exception.*;
import io.jsonwebtoken.Claims;
import model.Answer;
import model.Question;
import model.User;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.JwtService;
import service.QuestionService;
import wrapper.*;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static controller.ErrorMessages.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

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
    public ResponseEntity<Object> getQuestion(@PathVariable int id, HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User clientUser = jwtService.getUser(session, (Claims)request.getAttribute("claims"));
            Question question = questionService.getQuestionByID(session, id);
            if(clientUser.getID() != question.get_class().get_teacher().getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            return new ResponseEntity<Object>(new QuestionWrapper(question,false), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_QUESTION), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = DELETE)
    public ResponseEntity<Object> updateQuestion(@PathVariable int id,
                                                 HttpServletRequest request)  throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User clientUser = jwtService.getUser(session, (Claims) request.getAttribute("claims"));
            Question question = questionService.getQuestionByID(session, id);
            if (clientUser.getID() != question.get_class().get_teacher().getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            questionService.delete(session, question);

            return new ResponseEntity<Object>(OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_QUESTION), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } catch (EntityNotRemovableException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(QUESTION_NOT_REMOVABLE), NOT_ACCEPTABLE);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = PUT)
    public ResponseEntity<Object> updateQuestion(@PathVariable int id,
                                                 @RequestBody QuestionWrapper questionWrapper,
                                                 HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User clientUser = jwtService.getUser(session, (Claims)request.getAttribute("claims"));
            Question question = questionService.getQuestionByID(session, id);
            if(clientUser.getID() != question.get_class().get_teacher().getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            if (questionService.questionInUse(session, question))
                return new ResponseEntity<Object>(new ErrorWrapper(QUESTION_IN_USE), NOT_ACCEPTABLE);

            String text = questionWrapper.getText();
            String category = questionWrapper.getCategory();
            Integer difficulty = questionWrapper.getDifficulty();
            List<Answer> answers = getAnswersFromWrapper(questionWrapper);
            question = questionService.updateQuestion(session, question, text, category, difficulty, answers);

            return new ResponseEntity<Object>(new QuestionWrapper(question,false), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_QUESTION), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } catch (InvalidQuestionException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_QUESTION), NOT_ACCEPTABLE);
        } catch (ExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(QUESTION_EXISTS), NOT_ACCEPTABLE);
        } finally {
            session.close();
        }
    }

    private List<Answer> getAnswersFromWrapper(QuestionWrapper questionWrapper) throws InvalidQuestionException {
        List<AnswerWrapper> answerWrappers = questionWrapper.getAnswers();
        List<Answer> answers = new ArrayList<>();
        if(answerWrappers != null) {
            for (int i = 0; i < answerWrappers.size(); i++) {
                AnswerWrapper answerWrapper = answerWrappers.get(i);
                Answer answer = new Answer();
                answer.setText(answerWrapper.getText());
                if (answerWrapper.isCorrect() == null)
                    throw new InvalidQuestionException();
                answer.setCorrect(answerWrapper.isCorrect());
                answer.setOrder(i);
                answers.add(answer);
            }
        }
        return answers;
    }
}
