package service;

import dao.ExamDAO;
import dao.QuestionDAO;
import dao.QuestionScoreDAO;
import dao.QuestionScoreSetCollection;
import exception.ExistentEntityException;
import exception.InvalidExamException;
import exception.InvalidQuestionException;
import model.persistent.*;
import model.persistent.Class;
import org.orm.PersistentException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ExamServiceImpl implements ExamService{

    QuestionScoreDAO questionScoreDAO;
    ExamDAO examDAO;
    QuestionDAO questionDAO;

    public ExamServiceImpl(QuestionScoreDAO questionScoreDAO, ExamDAO examDAO, QuestionDAO questionDAO) {
        this.questionScoreDAO = questionScoreDAO;
        this.examDAO = examDAO;
        this.questionDAO = questionDAO;
    }

    @Override
    public Exam createExam(String name, int minutes, long beginDate, List<Integer> questionIDs, Group group)
            throws InvalidExamException, PersistentException, InvalidQuestionException, ExistentEntityException {
        Exam exam = new Exam();
        if(name == null || name.equals(""))
            throw new InvalidExamException("name");
        if(minutes < 10 || minutes > 300)
            throw new InvalidExamException("duration");
        if(beginDate < (System.currentTimeMillis()+ (1000 * 60 * 60 * 24)))
            throw new InvalidExamException("date");
        if(exists(group, name))
            throw new ExistentEntityException();
        if(hasDuplicates(questionIDs))
            throw new InvalidExamException("duplicates");


        exam.setName(name);
        exam.setDuration(minutes);
        exam.setBeginDate(beginDate);
        exam.set_group(group);
        Class cl = group.get_class();
        for (int qid : questionIDs) {
            Question question = questionDAO.getQuestionByORMID(qid);
            if(question == null || question.get_class().getID() != cl.getID())
                throw new InvalidQuestionException(""+qid);
            QuestionScore questionScore = new QuestionScore();
            questionScore.set_question(question);
            questionScore.setScore(20.0f / questionIDs.size()); //FIXME, DEFINIR COTACOES
            questionScore.set_exam(exam);
            exam._questions.add(questionScore);
        }
        return exam;
    }

    private boolean hasDuplicates(List<?> list){
        for(int i = 0; i<list.size(); i++){
            for(int j = i+1; j<list.size(); j++){
                if(list.get(i).equals(list.get(j)))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean exists(Group group, String examName) throws PersistentException {
        return examDAO.exists(group.getID(),examName);
    }

    @Override
    public boolean examContainsQuestion(Exam exam, Question question) throws PersistentException {
        return questionScoreDAO.exists(question.getID(),exam.getID());
    }

    @Override
    public Exam addExamToGroup(Group group, Exam exam) throws PersistentException, ExistentEntityException {
        if(exists(group, exam.getName()))
            throw new ExistentEntityException();
        exam.set_group(group);
        group._exams.add(exam);
        examDAO.save(exam);
        return exam;
    }
}
