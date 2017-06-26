package controller;

import exception.*;
import io.jsonwebtoken.Claims;
import model.persistent.*;
import model.persistent.Class;
import org.orm.PersistentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.JwtService;
import service.ExamService;
import service.GroupService;
import service.StudentService;
import wrapper.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static controller.ErrorMessages.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "api/groups")
public class GroupController {

    private JwtService jwtService;
    private GroupService groupService;
    private StudentService studentService;
    private ExamService examService;

    public GroupController(JwtService jwtService, GroupService groupService, StudentService studentService, ExamService examService) {
        this.jwtService = jwtService;
        this.groupService = groupService;
        this.studentService = studentService;
        this.examService = examService;
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = GET)
    public ResponseEntity<Object> getGroup(@PathVariable int id){
        try {
            Group group = this.groupService.getGroupByID(id);
            return new ResponseEntity<Object>(new GroupClassWrapper(group), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{groupID:[\\d]+}/students", method = POST)
    public ResponseEntity<Object> postStudents(@PathVariable int groupID, @RequestBody String[] studentEmails,
                                               HttpServletRequest request){
        try {
            User user = jwtService.getUser((Claims)request.getAttribute("claims"));
            Group group = groupService.getGroupByID(groupID);
            Class cl = group.get_class();

            if(cl.get_teacher().getID() != user.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            List<GroupStudentPOSTWrapper> groupStudents = new ArrayList<>();
            for(String email: studentEmails){
                try {
                    GroupStudent groupStudent = this.groupService.addStudentToGroupByEmail(group, email);
                    groupStudents.add(new GroupStudentPOSTWrapper(groupStudent));
                } catch (InvalidUserTypeException e) {
                    groupStudents.add(new GroupStudentPOSTWrapper(email, USER_IS_TEACHER));
                } catch (ExistentEntityException e) {
                    groupStudents.add(new GroupStudentPOSTWrapper(email, STUDENT_IN_GROUP));
                }
            }
            return new ResponseEntity<Object>(groupStudents, OK);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{groupID:[\\d]+}/students", method = GET)
    public ResponseEntity<Object> getStudents(@PathVariable int groupID){
        try {
            Group group = groupService.getGroupByID(groupID);
            Set<GroupStudentWrapper> groupStudents = new TreeSet<>();
            for(GroupStudent groupStudent: this.groupService.getGroupStudents(group)){
                groupStudents.add(new GroupStudentWrapper(groupStudent));
            }
            return new ResponseEntity<Object>(groupStudents, OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{groupID:[\\d]+}/students/{studentID:[\\d]+}", method = DELETE)
    public ResponseEntity<Object> removeStudent(@PathVariable int groupID, @PathVariable int studentID){
        Group group = null;
        try {
            group = groupService.getGroupByID(groupID);
            Student student = studentService.getStudentByID(studentID);
            groupService.removeStudentFromGroup(group,student);
            return new ResponseEntity<Object>(OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            if(group == null)
                return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
            else
                return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_STUDENT), NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{groupID:[\\d]+}/questions/available", method = GET)
    public ResponseEntity<Object> getAvailableQuestions(@PathVariable int groupID,
                                                        HttpServletRequest request){
        try {
            User user = jwtService.getUser((Claims)request.getAttribute("claims"));
            Group group = groupService.getGroupByID(groupID);
            Class cl = group.get_class();
            if(user.getID() != cl.get_teacher().getID()){
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);
            }

            Map<String, Map<Integer, List<Question>>> questions =
                    groupService.getAvailableQuestionsByCategoryAndDifficulty(group);
            return new ResponseEntity<Object>(wrapAvailableQuestions(questions), OK);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        }
    }

    private Map<String, Map<Integer, AvailableQuestionsWrapper>> wrapAvailableQuestions
                                    (Map<String, Map<Integer, List<Question>>> categoriesMap){
        Map<String, Map<Integer, AvailableQuestionsWrapper>> wrapper = new TreeMap<>();
        for(String category: categoriesMap.keySet()){
            wrapper.put(category, new TreeMap<>());
            Map<Integer, List<Question>> difficultiesMap = categoriesMap.get(category);
            for(int difficulty: difficultiesMap.keySet()){
                List<Question> questions = difficultiesMap.get(difficulty);
                wrapper.get(category).put(difficulty, new AvailableQuestionsWrapper(questions));
            }
        }
        return wrapper;
    }

    @RequestMapping(value = "/{groupID:[\\d]+}/exams", method = POST)
    public ResponseEntity<Object> postExam(@PathVariable int groupID,
                                           @RequestBody ExamPOSTWrapper examWrapper,
                                           HttpServletRequest request){
        try {
            User user = jwtService.getUser((Claims)request.getAttribute("claims"));
            Group group = groupService.getGroupByID(groupID);
            Class cl = group.get_class();
            if(user.getID() != cl.get_teacher().getID()){
                //return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);
            }

            Exam exam = examService.createExam(
                    examWrapper.getName(),
                    examWrapper.getDuration(),
                    examWrapper.getBeginDate(),
                    examWrapper.getQuestionIDs(),
                    group);
            exam = examService.addExamToGroup(group,exam);
            return new ResponseEntity<Object>(new ExamWrapper(exam, false, false), OK);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        } catch (InvalidExamException e) {
            switch (e.getMessage()){
                case "name":
                    return new ResponseEntity<Object>(new ErrorWrapper(INVALID_NAME), NOT_ACCEPTABLE);
                case "duration":
                    return new ResponseEntity<Object>(new ErrorWrapper(INVALID_DURATION), NOT_ACCEPTABLE);
                case "date":
                    return new ResponseEntity<Object>(new ErrorWrapper(INVALID_DATE), NOT_ACCEPTABLE);
                case "duplicates":
                    return new ResponseEntity<Object>(new ErrorWrapper(DUPLICATE_QUESTIONS), NOT_ACCEPTABLE);
                default:
                    return new ResponseEntity<Object>(new ErrorWrapper(INVALID_EXAM), NOT_ACCEPTABLE);
            }
        } catch (InvalidQuestionException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_QUESTION + " (id "+e.getMessage()+")"), NOT_ACCEPTABLE);
        } catch (ExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(EXISTENT_EXAM), NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/{groupID:[\\d]+}/exams/generate", method = POST)
    public ResponseEntity<Object> generateExam(@PathVariable int groupID,
                                               @RequestBody Question[] questions,
                                               HttpServletRequest request){
        try {
            User user = jwtService.getUser((Claims)request.getAttribute("claims"));
            Group group = groupService.getGroupByID(groupID);
            Class cl = group.get_class();
            if(user.getID() != cl.get_teacher().getID()){
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);
            }

            List<String> categories = new ArrayList<>();
            List<Integer> difficulties = new ArrayList<>();
            for(Question question: questions){
                String category = question.getCategory();
                int difficulty = question.getDifficulty();
                if(category == null || category.isEmpty() ||
                        difficulty < 1 || difficulty > 3)
                    return new ResponseEntity<Object>(new ErrorWrapper(INVALID_QUESTION), NOT_ACCEPTABLE);
                else{
                    categories.add(category);
                    difficulties.add(difficulty);
                }
            }
            List<Question> generatedQuestions = groupService.generateExamQuestions(group, categories, difficulties);
            List<QuestionWrapper> questionWrappers = new ArrayList<>();
            for(Question q: generatedQuestions)
                questionWrappers.add(new QuestionWrapper(q, false));
            return new ResponseEntity<Object>(questionWrappers, OK);
        } catch (PersistentException | InvalidInputException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        } catch (InsufficientQuestionsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INSUFFICIENT_QUESTIONS), NOT_ACCEPTABLE);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/{groupID:[\\d]+}/exams", method = GET)
    public ResponseEntity<Object> getExams(@PathVariable int groupID, HttpServletRequest request){
        try {
            User user = jwtService.getUser((Claims)request.getAttribute("claims"));
            Group group = groupService.getGroupByID(groupID);

            if(!groupService.userHasAccess(group,user))
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), UNAUTHORIZED);

            ExamsWrapper examsWrapper = new ExamsWrapper(examService.getExamsByGroup(group), false);
            return new ResponseEntity<Object>(examsWrapper, OK);
        } catch (PersistentException e){
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_USER), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        }
    }
}
