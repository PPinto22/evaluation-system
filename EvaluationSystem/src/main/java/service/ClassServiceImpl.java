package service;

import dao.ClassDAO;
import dao.QuestionDAO;
import dao.TeacherDAO;
import exception.ExistentEntityException;
import exception.InvalidQuestionException;
import exception.MissingInformationException;
import exception.NonExistentEntityException;
import model.Class;
import model.Group;
import model.Question;
import model.Teacher;
import org.orm.PersistentException;
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
    public Class getClassByID(int id) throws PersistentException, NonExistentEntityException {
        if(!classDAO.exists(id))
            throw new NonExistentEntityException();
        return this.classDAO.loadClassByORMID(id);
    }

    @Override
    public Class getClassByName(Teacher teacher, String className) throws PersistentException, NonExistentEntityException {
        if(!classDAO.exists(teacher.getID(), className))
            throw new NonExistentEntityException();

        return this.classDAO.getClassByName(teacher.getID(),className);
    }

    @Override
    public Class addClass(Class cl) throws PersistentException, MissingInformationException {
        if(cl.isMissingInformation())
            throw new MissingInformationException();
        this.classDAO.save(cl);
        return cl;
    }

    @Override
    public Group addGroupToClass(Class cl, Group group) throws PersistentException, ExistentEntityException {
        if(this.groupService.exists(cl, group.getName()))
            throw new ExistentEntityException();

        cl._groups.add(group);
        group.set_class(cl);
        return this.groupService.addGroup(group);
    }


    @Override
    public List<Question> listClassQuestions(Class cl) throws PersistentException {
        return questionDAO.listQuestionsByClass(cl.getID());
    }

    @Override
    public List<Question> listClassQuestionsByCategoryAndDifficulty(Class cl, String category, int difficulty) throws PersistentException {
        return questionDAO.listQuestionsByClassCategoryAndDifficulty(cl.getID(), category, difficulty);
    }

    @Override
    public Set<String> getClassCategories(Class cl) throws PersistentException {
        List<Question> questions = this.listClassQuestions(cl);
        Set<String> categories = new TreeSet<>();
        for(Question question: questions)
            categories.add(question.getCategory());
        return categories;
    }

    @Override
    public Question addQuestionToClass(Class cl, Question question)
            throws InvalidQuestionException, ExistentEntityException, PersistentException {
        if(!questionService.validate(question))
            throw new InvalidQuestionException();

        if(questionService.exists(cl, question))
            throw new ExistentEntityException();

        cl._question.add(question);
        question.set_class(cl);
        questionDAO.save(question);
        return question;
    }

    @Override
    public void delete(Class cl) throws PersistentException {
        Teacher teacher = cl.get_teacher();
        teacher._classes.remove(cl);
        this.classDAO.delete(cl);
    }

    @Override
    public boolean exists(int id) throws PersistentException {
        return this.classDAO.exists(id);
    }

    @Override
    public boolean exists(Teacher teacher, String className) throws PersistentException {
        return this.classDAO.exists(teacher.getID(), className);
    }
}
