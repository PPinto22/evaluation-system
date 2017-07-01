package controller;

import dao.ClassesPersistentManager;
import exception.*;
import io.jsonwebtoken.Claims;
import model.*;
import model.Class;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.JwtService;
import service.*;
import wrapper.*;

import java.util.HashMap;
import java.util.Map;

import static controller.ErrorMessages.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "api/exams")
public class ExamController {

    private ExamService examService;
    private GroupService groupService;
    private JwtService jwtService;
    private SubmissionService submissionService;
    private QuestionService questionService;
    private AnswerService answerService;

    public ExamController(ExamService examService, GroupService groupService, UserService userService,
                          JwtService jwtService, SubmissionService submissionService,
                          QuestionService questionService, AnswerService answerService) {
        this.examService = examService;
        this.groupService = groupService;
        this.jwtService = jwtService;
        this.submissionService = submissionService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @RequestMapping(value = "/{examID:[\\d]+}", method = PUT)
    public ResponseEntity<Object> getExam(@PathVariable int examID,
                                          @RequestBody ExamPUTWrapper examWrapper,
                                          HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = jwtService.getUser(session, (Claims) request.getAttribute("claims"));
            Exam exam = examService.getExamByID(session, examID);
            Group group = exam.get_group();
            Class cl = group.get_class();
            if(cl.get_teacher().getID() != user.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);
            exam = examService.updateExam(session, exam, examWrapper.getName(), examWrapper.getBeginDate(), examWrapper.getDuration());
            return new ResponseEntity<Object>(new ExamWrapper(exam, true, true), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_EXAM), NOT_FOUND);
        } catch (ExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(EXAM_EXISTS), NOT_ACCEPTABLE);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{examID:[\\d]+}", method = DELETE)
    public ResponseEntity<Object> deleteExam(@PathVariable int examID,
                                             HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = jwtService.getUser(session, (Claims) request.getAttribute("claims"));
            Exam exam = examService.getExamByID(session, examID);
            Group group = exam.get_group();
            Class cl = group.get_class();
            if(cl.get_teacher().getID() != user.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);
            examService.deleteExam(session, exam);
            return new ResponseEntity<Object>(new Object(), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_EXAM), NOT_FOUND);
        } catch (EntityNotRemovableException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(EXAM_NOT_REMOVABLE), NOT_ACCEPTABLE);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{examID:[\\d]+}", method = GET)
    public ResponseEntity<Object> getExam(@PathVariable int examID, HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = jwtService.getUser(session, (Claims)request.getAttribute("claims"));
            Exam exam = examService.getExamByID(session, examID);
            Group group = exam.get_group();
            Class cl = group.get_class();
            switch (user.getClass().getSimpleName()){
                case "Teacher":
                    if(user.getID() == cl.get_teacher().getID())
                        return new ResponseEntity<Object>(new ExamWrapper(exam,false, false), OK);
                    return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);
                case "Student":
                    Student student = (Student)user;
                    if(!groupService.studentInGroup(student,group))
                        return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);
                    if(examService.examHasStarted(exam))
                        return new ResponseEntity<Object>(new ExamWrapper(exam, false, true), OK);
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
        } finally {
            session.close();
        }
    }


    @RequestMapping(value = "/{examID:[\\d]+}/submissions", method = POST)
    public ResponseEntity<Object> postSubmission(@PathVariable int examID,
                                                 @RequestBody Map<Integer, Integer> answersMap,
                                                 HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = jwtService.getUser(session, (Claims)request.getAttribute("claims"));
            Exam exam = examService.getExamByID(session, examID);
            Group group = exam.get_group();
            if(!(user instanceof Student))
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            Student student = (Student) user;
            if (!groupService.studentInGroup(student, group))
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);
            if (submissionService.exists(session, student, exam))
                return new ResponseEntity<>(new ErrorWrapper(SUBMISSION_EXISTS), NOT_ACCEPTABLE);

            Map<Question, Answer> answers = this.getAnswers(session, answersMap);
            Submission submission = submissionService.submit(session, student, exam, answers);
            return new ResponseEntity<Object>(new SubmissionWrapper(submission, false, true), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_EXAM), NOT_FOUND);
        } catch (InvalidAnswerException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_ANSWER), NOT_ACCEPTABLE);
        } catch (InvalidQuestionException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_QUESTION), NOT_ACCEPTABLE);
        } catch (ExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(SUBMISSION_EXISTS), NOT_ACCEPTABLE);
        } finally {
            session.close();
        }
    }

    private Map<Question, Answer> getAnswers(PersistentSession session, Map<Integer, Integer> answersMap)
            throws PersistentException, NonExistentEntityException {
        Map<Question, Answer> answers = new HashMap<>();
        for(int qid: answersMap.keySet()){
            Question question = questionService.getQuestionByID(session, qid);
            Answer answer = answerService.getAnswerByID(session, answersMap.get(qid));
            answers.put(question, answer);
        }
        return answers;
    }

    @RequestMapping(value = "/{examID:[\\d]+}/scores", method = GET)
    public ResponseEntity<Object> getScores(@PathVariable int examID, HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = jwtService.getUser(session, (Claims)request.getAttribute("claims"));
            Exam exam = examService.getExamByID(session, examID);

            if(!groupService.userHasAccess(exam.get_group(),user))
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);
            Map<Student, Score> scoreMap = examService.getExamScores(session, exam);
            return new ResponseEntity<Object>(new StudentsScoresWrapper(scoreMap), OK);
        } catch (PersistentException e){
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } catch (InvalidExamException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_EXAM), NOT_ACCEPTABLE);
        } finally {
            session.close();
        }
    }

}
