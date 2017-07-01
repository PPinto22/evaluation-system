package service;

import dao.ClassDAO;
import dao.QuestionDAO;
import dao.TeacherDAO;
import exception.*;
import model.Class;
import model.Group;
import model.Question;
import model.Teacher;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class ClassServiceImpl implements ClassService{

    ClassDAO classDAO;
    TeacherDAO teacherDAO;
    QuestionDAO questionDAO;
    GroupService groupService;
    TeacherService teacherService;
    QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }
    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }
    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public ClassServiceImpl(ClassDAO classDAO, TeacherDAO teacherDAO, QuestionDAO questionDAO) {
        this.classDAO = classDAO;
        this.teacherDAO = teacherDAO;
        this.questionDAO = questionDAO;
    }

    @Override
    public Class updateClass(PersistentSession session, Class cl, String name, String abbreviation)
            throws PersistentException, ExistentEntityException {
        if(name != null && !name.equals(cl.getName()) && exists(session, cl.get_teacher(), name))
            throw new ExistentEntityException();
        if(name != null && !name.equals(""))
            cl.setName(name);
        if(abbreviation != null && !name.equals(""))
            cl.setAbbreviation(abbreviation);

        classDAO.save(cl);
        return cl;
    }

    @Override
    public Class getClassByID(PersistentSession session, int id) throws PersistentException, NonExistentEntityException {
        if(!classDAO.exists(session, id))
            throw new NonExistentEntityException();
        return this.classDAO.loadClassByORMID(session, id);
    }

    @Override
    public Class getClassByName(PersistentSession session, Teacher teacher, String className) throws PersistentException, NonExistentEntityException {
        if(!classDAO.exists(session, teacher.getID(), className))
            throw new NonExistentEntityException();

        return classDAO.getClassByName(session, teacher.getID(), className);
    }

    @Override
    public Class addClass(Class cl) throws PersistentException, MissingInformationException {
        if(cl.isMissingInformation())
            throw new MissingInformationException();
        this.classDAO.save(cl);
        return cl;
    }

    @Override
    public Group addGroupToClass(PersistentSession session, Class cl, Group group) throws PersistentException, ExistentEntityException {
        if(this.groupService.exists(session, cl, group.getName()))
            throw new ExistentEntityException();

        cl._groups.add(group);
        group.set_class(cl);
        return this.groupService.addGroup(group);
    }


    @Override
    public List<Question> listClassQuestions(PersistentSession session, Class cl) throws PersistentException {
        return questionDAO.listQuestionsByClass(session, cl.getID());
    }

    @Override
    public List<Question> listClassQuestionsByCategoryAndDifficulty(PersistentSession session,
                                                                    Class cl, String category,
                                                                    int difficulty) throws PersistentException {
        return questionDAO.listQuestionsByClassCategoryAndDifficulty(session, cl.getID(), category, difficulty);
    }

    @Override
    public Set<String> getClassCategories(PersistentSession session, Class cl) throws PersistentException {
        List<Question> questions = this.listClassQuestions(session, cl);
        Set<String> categories = new TreeSet<>();
        for(Question question: questions)
            categories.add(question.getCategory());
        return categories;
    }

    @Override
    public Question addQuestionToClass(PersistentSession session, Class cl, Question question)
            throws InvalidQuestionException, ExistentEntityException, PersistentException {
        if(!questionService.validate(question))
            throw new InvalidQuestionException();

        if(questionService.exists(session, cl, question))
            throw new ExistentEntityException();

        cl._question.add(question);
        question.set_class(cl);
        questionDAO.save(question);
        return question;
    }

    @Override
    public void delete(PersistentSession session, Class cl) throws PersistentException, EntityNotRemovableException {
        if(classHasSubmissions(session, cl))
            throw new EntityNotRemovableException();
        Teacher teacher = cl.get_teacher();
        for(Group group: cl._groups.toArray()){
            groupService.delete(session, group);
        }
        for(Question question: cl._question.toArray()){
            questionService.delete(session, question);
        }
        teacher._classes.remove(cl);
        this.classDAO.delete(cl);
    }

    public boolean classHasSubmissions(PersistentSession session, Class cl) throws PersistentException {
        for(Group group: cl._groups.toArray()){
            if(groupService.groupHasSubmissions(session, group))
                return true;
        }
        return false;
    }

    @Override
    public boolean exists(PersistentSession session, int id) throws PersistentException {
        return this.classDAO.exists(session, id);
    }

    @Override
    public boolean exists(PersistentSession session, Teacher teacher, String className) throws PersistentException {
        return this.classDAO.exists(session, teacher.getID(), className);
    }
}
