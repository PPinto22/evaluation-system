package service;

import dao.ExamDAO;
import dao.QuestionScoreDAO;
import model.persistent.Exam;
import model.persistent.Question;
import org.orm.PersistentException;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService{

    QuestionScoreDAO questionScoreDAO;
    ExamDAO examDAO;

    public ExamServiceImpl(QuestionScoreDAO questionScoreDAO, ExamDAO examDAO) {
        this.questionScoreDAO = questionScoreDAO;
        this.examDAO = examDAO;
    }

    @Override
    public boolean examContainsQuestion(Exam exam, Question question) throws PersistentException {
        return questionScoreDAO.exists(question.getID(),exam.getID());
    }
}
