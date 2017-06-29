package controller;

import exception.*;
import io.jsonwebtoken.Claims;
import model.*;
import model.Class;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.orm.PersistentException;
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
                                          HttpServletRequest request) {
        try {
            User user = jwtService.getUser((Claims) request.getAttribute("claims"));
            Exam exam = examService.getExamByID(examID);
            Group group = exam.get_group();
            Class cl = group.get_class();
            if(cl.get_teacher().getID() != user.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            exam = examService.updateExam(exam, examWrapper.getName(), examWrapper.getBeginDate(), examWrapper.getDuration());
            return new ResponseEntity<Object>(new ExamWrapper(exam, true, true), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_EXAM), NOT_FOUND);
        } catch (ExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(EXISTENT_EXAM), NOT_ACCEPTABLE);
        }
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


    @RequestMapping(value = "/{examID:[\\d]+}/submissions", method = POST)
    public ResponseEntity<Object> postSubmission(@PathVariable int examID,
                                                 @RequestBody Map<Integer, Integer> answersMap,
                                                 HttpServletRequest request){
        try{
            User user = jwtService.getUser((Claims)request.getAttribute("claims"));
            Exam exam = examService.getExamByID(examID);
            Group group = exam.get_group();
            if(!(user instanceof Student))
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);
            Student student = (Student)user;
            if(!groupService.studentInGroup(student,group))
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            if(submissionService.exists(student,exam))
                return new ResponseEntity<>(new ErrorWrapper(EXISTENT_SUBMISSION), NOT_ACCEPTABLE);

            Map<Question, Answer> answers = this.getAnswers(answersMap);
            Submission submission = submissionService.submit(student, exam, answers);
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
            return new ResponseEntity<Object>(new ErrorWrapper(EXISTENT_SUBMISSION), NOT_ACCEPTABLE);
        }
    }

    private Map<Question, Answer> getAnswers(Map<Integer, Integer> answersMap) throws PersistentException, NonExistentEntityException {
        Map<Question, Answer> answers = new HashMap<>();
        for(int qid: answersMap.keySet()){
            Question question = questionService.getQuestionByID(qid);
            Answer answer = answerService.getAnswerByID(answersMap.get(qid));
            answers.put(question, answer);
        }
        return answers;
    }

    @RequestMapping(value = "/{examID:[\\d]+}/scores", method = GET)
    public ResponseEntity<Object> getScores(@PathVariable int examID, HttpServletRequest request){
        try {
            User user = jwtService.getUser((Claims)request.getAttribute("claims"));
            Exam exam = examService.getExamByID(examID);

            if(!groupService.userHasAccess(exam.get_group(),user))
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            Map<Student, Score> scoreMap = examService.getExamScores(exam);
            return new ResponseEntity<Object>(new StudentsScoresWrapper(scoreMap), OK);
        } catch (PersistentException e){
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } catch (InvalidExamException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_EXAM), NOT_ACCEPTABLE);
        }
    }

}
