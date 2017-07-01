package controller;

import dao.ClassesPersistentManager;
import exception.InvalidAnswerException;
import exception.InvalidClaimsException;
import exception.InvalidQuestionException;
import exception.NonExistentEntityException;
import io.jsonwebtoken.Claims;
import model.Answer;
import model.Question;
import model.Submission;
import model.User;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.JwtService;
import service.AnswerService;
import service.ExamService;
import service.QuestionService;
import service.SubmissionService;
import wrapper.ErrorWrapper;
import wrapper.SubmissionExamWrapper;
import wrapper.SubmissionWrapper;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static controller.ErrorMessages.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "api/submissions")
public class SubmissionController {

    private JwtService jwtService;
    private SubmissionService submissionService;
    private ExamService examService;
    private QuestionService questionService;
    private AnswerService answerService;

    public SubmissionController(JwtService jwtService, SubmissionService submissionService,
                                ExamService examService, QuestionService questionService, AnswerService answerService) {
        this.jwtService = jwtService;
        this.submissionService = submissionService;
        this.examService = examService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    // TODO validar utilizador
    @RequestMapping(value = "/{submissionID:[\\d]+}", method = GET)
    public ResponseEntity<Object> getSubmission(@PathVariable int submissionID, HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            Submission submission = submissionService.getSubmissionByID(session, submissionID);
            boolean hideAnswers = !examService.examHasFinished(submission.get_exam());
            return new ResponseEntity<Object>(new SubmissionExamWrapper(submission, false, hideAnswers), OK);
        } catch (PersistentException e){
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_SUBMISSION), NOT_FOUND);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{submissionID:[\\d]+}", method = DELETE)
    public ResponseEntity<Object> deleteSubmission(@PathVariable int submissionID, HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = jwtService.getUser(session, (Claims) request.getAttribute("claims"));
            Submission submission = submissionService.getSubmissionByID(session, submissionID);
            if(submission.get_student().getID() != user.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            submissionService.deleteSubmission(submission);
            return new ResponseEntity<Object>(new Object(), OK);
        } catch (PersistentException e){
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_SUBMISSION), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } finally {
            session.close();
        }
    }

    // TODO validar utilizador
    @RequestMapping(value = "/{submissionID:[\\d]+}", method = PUT)
    public ResponseEntity<Object> updateSubmission(@PathVariable int submissionID,
                                                   @RequestBody Map<Integer, Integer> answersMap,
                                                   HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            Submission submission = submissionService.getSubmissionByID(session, submissionID);
            Map<Question, Answer> answers = getAnswers(session, answersMap);
            submission = submissionService.updateSubmission(session, submission, answers);
            return new ResponseEntity<Object>(new SubmissionWrapper(submission, false, true), OK);
        } catch (PersistentException e){
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_SUBMISSION), NOT_FOUND);
        } catch (InvalidAnswerException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_ANSWER), NOT_ACCEPTABLE);
        } catch (InvalidQuestionException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_QUESTION), NOT_ACCEPTABLE);
        } finally {
            session.close();
        }
    }
    private Map<Question, Answer> getAnswers(PersistentSession session, Map<Integer, Integer> answersMap) throws PersistentException, NonExistentEntityException {
        Map<Question, Answer> answers = new HashMap<>();
        for(int qid: answersMap.keySet()){
            Question question = questionService.getQuestionByID(session, qid);
            Answer answer = answerService.getAnswerByID(session, answersMap.get(qid));
            answers.put(question, answer);
        }
        return answers;
    }
}
