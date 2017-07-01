package controller;

import dao.ClassesPersistentManager;
import exception.*;
import io.jsonwebtoken.Claims;
import model.*;
import model.Class;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import security.JwtService;
import service.ExamService;
import service.GroupService;
import service.QuestionService;
import service.StudentService;
import wrapper.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static controller.ErrorMessages.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "api/groups")
public class GroupController {

    private JwtService jwtService;
    private GroupService groupService;
    private StudentService studentService;
    private ExamService examService;
    private QuestionService questionService;

    public GroupController(JwtService jwtService, GroupService groupService,
                           StudentService studentService, ExamService examService,
                           QuestionService questionService) {
        this.jwtService = jwtService;
        this.groupService = groupService;
        this.studentService = studentService;
        this.examService = examService;
        this.questionService = questionService;
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = GET)
    public ResponseEntity<Object> getGroup(@PathVariable int id) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            Group group = this.groupService.getGroupByID(session, id);
            return new ResponseEntity<Object>(new GroupClassWrapper(group), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        } finally {
            session.close();
        }
    }
    
    @RequestMapping(value = "/{id:[\\d]+}", method = PUT)
    public ResponseEntity<Object> updateGroup(@PathVariable int id,
                                              @RequestBody Group groupWrapper,
                                              HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User clientUser = jwtService.getUser(session,(Claims) request.getAttribute("claims"));
            Group group = this.groupService.getGroupByID(session, id);
            if(group.get_class().get_teacher().getID() != clientUser.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            group = groupService.updateGroup(session, group, groupWrapper.getName());
            return new ResponseEntity<Object>(new GroupClassWrapper(group), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } catch (ExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(GROUP_EXISTS), NOT_ACCEPTABLE);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = DELETE)
    public ResponseEntity<Object> deleteGroup(@PathVariable int id,
                                              HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User clientUser = jwtService.getUser(session,(Claims) request.getAttribute("claims"));
            Group group = this.groupService.getGroupByID(session, id);
            if(group.get_class().get_teacher().getID() != clientUser.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            groupService.delete(session, group);
            return new ResponseEntity<Object>(new Object(), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } catch (EntityNotRemovableException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(GROUP_NOT_REMOVABLE), NOT_ACCEPTABLE);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{groupID:[\\d]+}/students", method = POST)
    public ResponseEntity<Object> postStudents(@PathVariable int groupID, @RequestBody String[] studentEmails,
                                               HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = jwtService.getUser(session,(Claims)request.getAttribute("claims"));
            Group group = groupService.getGroupByID(session, groupID);
            Class cl = group.get_class();

            if(cl.get_teacher().getID() != user.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            List<GroupStudentPOSTWrapper> groupStudents = new ArrayList<>();
            for(String email: studentEmails){
                try {
                    GroupStudent groupStudent = this.groupService.addStudentToGroupByEmail(session, group, email);
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
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{groupID:[\\d]+}/students", method = GET)
    public ResponseEntity<Object> getStudents(@PathVariable int groupID) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            Group group = groupService.getGroupByID(session, groupID);
            Set<GroupStudentWrapper> groupStudents = new TreeSet<>();
            for(GroupStudent groupStudent: this.groupService.getGroupStudents(group)){
                groupStudents.add(new GroupStudentWrapper(groupStudent));
            }
            return new ResponseEntity<Object>(groupStudents, OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{groupID:[\\d]+}/students/{studentID:[\\d]+}", method = DELETE)
    public ResponseEntity<Object> removeStudent(@PathVariable int groupID,
                                                @PathVariable int studentID,
                                                HttpServletRequest request) throws PersistentException{
        Group group = null;
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = jwtService.getUser(session,(Claims)request.getAttribute("claims"));
            group = groupService.getGroupByID(session, groupID);
            if(group.get_class().get_teacher().getID() != user.getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            Student student = studentService.getStudentByID(session, studentID);
            groupService.removeStudentFromGroup(session, group, student);
            return new ResponseEntity<Object>(new Object(), OK);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            if(group == null)
                return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
            else
                return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_STUDENT), NOT_FOUND);
        } catch (EntityNotRemovableException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(STUDENT_NOT_REMOVABLE), NOT_ACCEPTABLE);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{groupID:[\\d]+}/questions/available", method = GET)
    public ResponseEntity<Object> getAvailableQuestions(@PathVariable int groupID,
                                                        HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = jwtService.getUser(session,(Claims)request.getAttribute("claims"));
            Group group = groupService.getGroupByID(session, groupID);
            Class cl = group.get_class();
            if(user.getID() != cl.get_teacher().getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            Map<String, Map<Integer, List<Question>>> questions =
                    groupService.getAvailableQuestions(session, group);
            return new ResponseEntity<Object>(wrapAvailableQuestions(questions), OK);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        } finally {
            session.close();
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
                                           HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = jwtService.getUser(session,(Claims)request.getAttribute("claims"));
            Group group = groupService.getGroupByID(session, groupID);
            Class cl = group.get_class();
            if(user.getID() != cl.get_teacher().getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            Exam exam = examService.createExam(session,
                    examWrapper.getName(),
                    examWrapper.getDuration(),
                    examWrapper.getBeginDate(),
                    examWrapper.getQuestionIDs(), group);
            exam = examService.addExamToGroup(session, group, exam);
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
            return new ResponseEntity<Object>(new ErrorWrapper(EXAM_EXISTS), NOT_ACCEPTABLE);
        } finally {
            session.close();
        }
    }

    @RequestMapping(value = "/{groupID:[\\d]+}/exams/generate/question", method = POST)
    public ResponseEntity<Object> generateExamQuestion(@PathVariable int groupID,
                                                       @RequestBody GenerateExamQuestionWrapper questionWrapper,
                                                       HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = jwtService.getUser(session,(Claims)request.getAttribute("claims"));
            Group group = groupService.getGroupByID(session, groupID);
            Class cl = group.get_class();
            if(user.getID() != cl.get_teacher().getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            String category = questionWrapper.getCategory();
            int difficulty = questionWrapper.getDifficulty();
            if(category == null || category.isEmpty() ||
                    difficulty < 1 || difficulty > 3)
                return new ResponseEntity<Object>(new ErrorWrapper(INVALID_QUESTION), NOT_ACCEPTABLE);

            try {
                List<Question> excludedQuestions = questionService.listQuestionsByIDs(session, questionWrapper.getExcluded());
                Question question = examService.generateExamQuestion(session, group, category, difficulty, excludedQuestions);
                return new ResponseEntity<Object>(new QuestionWrapper(question, false), OK);
            } catch(NonExistentEntityException e){
                return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_QUESTION), NOT_ACCEPTABLE);
            }
        } catch (PersistentException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(PERSISTENT_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        } catch (InsufficientQuestionsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INSUFFICIENT_QUESTIONS), NOT_ACCEPTABLE);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } finally {
            session.close();
        }
    }

        @RequestMapping(value = "/{groupID:[\\d]+}/exams/generate", method = POST)
    public ResponseEntity<Object> generateExam(@PathVariable int groupID,
                                               @RequestBody Question[] questions,
                                               HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = jwtService.getUser(session,(Claims)request.getAttribute("claims"));
            Group group = groupService.getGroupByID(session, groupID);
            Class cl = group.get_class();
            if(user.getID() != cl.get_teacher().getID())
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            List<String> categories = new ArrayList<>();
            List<Integer> difficulties = new ArrayList<>();
            for(Question question: questions){
                String category = question.getCategory();
                int difficulty = question.getDifficulty();
                if(category == null || category.isEmpty() ||
                        difficulty < 1 || difficulty > 3)
                    return new ResponseEntity<Object>(new ErrorWrapper(INVALID_QUESTION), NOT_ACCEPTABLE);
                categories.add(category);
                difficulties.add(difficulty);
            }
            List<Question> generatedQuestions = examService.generateExamQuestions(session, group, categories, difficulties);
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
        } finally {
            session.close();
        }
    }


    @RequestMapping(value = "/{groupID:[\\d]+}/exams", method = GET)
    public ResponseEntity<Object> getExams(@PathVariable int groupID,
                                           @RequestParam(required = false) String ongoing,
                                           HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = jwtService.getUser(session,(Claims)request.getAttribute("claims"));
            Group group = groupService.getGroupByID(session, groupID);

            if(!groupService.userHasAccess(group,user))
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            if(ongoing != null){
                return new ResponseEntity<Object>(examService.getOngoingExamsByGroup(group), OK);
            }
            ExamsWrapper examsWrapper = new ExamsWrapper(examService.getExamsByGroup(group), false);
            return new ResponseEntity<Object>(examsWrapper, OK);
        } catch (PersistentException e){
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } finally {
            session.close();
        }
    }

    private List<ExamWrapper> wrapExamList(Collection<Exam> exams){
        List<ExamWrapper> examWrappers = new ArrayList<>();
        for(Exam exam: exams){
            examWrappers.add(new ExamWrapper(exam,true,true));
        }
        return examWrappers;
    }

    @RequestMapping(value = "/{groupID:[\\d]+}/scores", method = GET)
    public ResponseEntity<Object> getScores(@PathVariable int groupID, HttpServletRequest request) throws PersistentException{
        PersistentSession session = null;
        try {
            session = ClassesPersistentManager.instance().getSession();
            User user = jwtService.getUser(session,(Claims)request.getAttribute("claims"));
            Group group = groupService.getGroupByID(session, groupID);

            if(!groupService.userHasAccess(group,user))
                return new ResponseEntity<Object>(new ErrorWrapper(NO_PERMISSION), FORBIDDEN);

            Map<Student, Map<Exam, Score>> scoreMap = groupService.getGroupScores(session, group);
            return new ResponseEntity<Object>(new StudentsExamsScoresWrapper(scoreMap), OK);
        } catch (PersistentException e){
            return new ResponseEntity<Object>(new ErrorWrapper(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
        } catch (NonExistentEntityException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(NO_SUCH_GROUP), NOT_FOUND);
        } catch (InvalidClaimsException e) {
            return new ResponseEntity<Object>(new ErrorWrapper(INVALID_TOKEN), UNAUTHORIZED);
        } finally {
            session.close();
        }
    }
}
