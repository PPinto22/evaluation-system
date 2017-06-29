package service;

import dao.AnswerDAO;
import dao.QuestionDAO;
import dao.QuestionScoreDAO;
import exception.ExistentEntityException;
import exception.InvalidQuestionException;
import exception.NonExistentEntityException;
import model.Answer;
import model.Question;
import model.Class;
import org.orm.PersistentException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    private QuestionDAO questionDAO;
    private QuestionScoreDAO questionScoreDAO;
    private AnswerDAO answerDAO;

    public QuestionServiceImpl(QuestionDAO questionDAO,
                               QuestionScoreDAO questionScoreDAO, AnswerDAO answerDAO) {
        this.questionDAO = questionDAO;
        this.questionScoreDAO = questionScoreDAO;
        this.answerDAO = answerDAO;
    }

    @Override
    public Question updateQuestion(Question question, String text, String category, Integer difficulty, List<Answer> answers)
            throws InvalidQuestionException, PersistentException, ExistentEntityException {
        String newText = (text == null || text.equals("")) ? question.getText() : text;
        String newCategory = (category == null || category.equals("")) ? question.getCategory() : category;
        Integer newDifficulty = difficulty == null ? question.getDifficulty() : difficulty;
        List<Answer> newAnswers = (answers == null || answers.isEmpty()) ? question.getAnswers() : answers;

        Question newQuestion = new Question();
        newQuestion.setText(newText);
        newQuestion.setCategory(newCategory);
        newQuestion.setDifficulty(newDifficulty);
        for(Answer answer: newAnswers)
            newQuestion._answers.add(answer);

        if(!validate(newQuestion))
            throw new InvalidQuestionException();

        if(!newQuestion.getText().equals(question.getText()) && !sameAnswers(question,newQuestion)){
            if(exists(question.get_class(), newQuestion))
                throw new ExistentEntityException();
        }

        question.setText(newText);
        question.setCategory(newCategory);
        question.setDifficulty(newDifficulty);
        question._answers.clear();
        for(Answer answer: newAnswers)
            question._answers.add(answer);
        questionDAO.save(question);
        return question;
    }

    @Override
    public boolean questionInUse(Question question) throws PersistentException {
        return questionScoreDAO.exists(question.getID());
    }

    @Override
    public List<Question> listQuestionsByIDs(List<Integer> IDs) throws PersistentException, NonExistentEntityException {
        List<Question> questions = new ArrayList<>();
        for(int id: IDs){
            questions.add(getQuestionByID(id));
        }
        return questions;
    }

    @Override
    public boolean questionContainsAnswer(Question question, Answer answer) {
        return question._answers.contains(answer);
    }

    @Override
    public Question getQuestionByID(int ID) throws PersistentException, NonExistentEntityException {
        if(!this.exists(ID))
            throw new NonExistentEntityException();
        return questionDAO.loadQuestionByORMID(ID);
    }

    @Override
    public boolean exists(int ID) throws PersistentException {
        return questionDAO.getQuestionByORMID(ID) != null;
    }

    @Override
    public boolean exists(Class cl, Question question) throws PersistentException {
        List<Question> questions = questionDAO.listQuestionsByClassAndText(cl.getID(),question.getText());
        if(questions.isEmpty())
            return false;

        for(Question existentQuestion: questions){
            if(sameAnswers(question, existentQuestion))
                return true;
        }
        return false;
    }

    private boolean sameAnswers(Question question, Question otherQuestion){
        if(question._answers.size() != otherQuestion._answers.size())
            return false;

        for(Answer answer: question._answers.toArray()){
            boolean contains = false;
            Answer[] otherAnswers = otherQuestion._answers.toArray();
            for(int i = 0; i < otherAnswers.length && !contains; i++){
                if (answer.getText().toLowerCase().equals(otherAnswers[i].getText().toLowerCase()))
                    contains = true;
            }
            if(!contains)
                return false;
        }
        return true;
    }

    @Override
    public boolean validate(Question question) {
        if(question.getText() == null || question.getText().equals("")
            || question.getCategory() == null || question.getCategory().equals("")
            || question.getDifficulty() > 3 || question.getDifficulty() < 1
            || question._answers.size() > 10 || question._answers.size() < 2)
            return false;
        int correct = 0;
        for(Answer answer: question._answers.toArray()){
            if(answer.getText() == null || answer.getText().equals(""))
                return false;

            if(answer.isCorrect()) {
                correct++;
                if(correct > 1)
                    return false;
            }
        }
        return correct == 1;
    }
}
