package service;

import dao.SubmissionDAO;
import exception.ExistentEntityException;
import exception.InvalidAnswerException;
import exception.InvalidQuestionException;
import exception.NonExistentEntityException;
import model.persistent.*;
import org.orm.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubmissionServiceImpl implements SubmissionService{

    SubmissionDAO submissionDAO;
    ExamService examService;
    QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }
    @Autowired
    public void setExamService(ExamService examService) {
        this.examService = examService;
    }
    public SubmissionServiceImpl(SubmissionDAO submissionDAO) {
        this.submissionDAO = submissionDAO;
    }

    @Override
    public Submission submit(Student student, Exam exam, Map<Question, Answer> answers)
            throws ExistentEntityException, PersistentException, InvalidQuestionException, InvalidAnswerException {
        if(exists(student,exam))
            throw new ExistentEntityException();

        Submission submission = new Submission();
        submission.set_student(student);
        submission.set_exam(exam);
        addAnswersToSubmission(submission, answers);
        submissionDAO.save(submission);
        return submission;
    }

    private void addAnswersToSubmission(Submission submission, Map<Question, Answer> answers)
            throws InvalidQuestionException, InvalidAnswerException, PersistentException {
        for(Question question: answers.keySet()){
            Answer answer = answers.get(question);
            if(!examService.examContainsQuestion(submission.get_exam(),question))
                throw new InvalidQuestionException();
            if(!questionService.questionContainsAnswer(question,answer))
                throw new InvalidAnswerException();

            QuestionSubmission qSub = null;
            try {
                qSub = getQuestionSubmission(submission, question);
                qSub.set_answer(answer);
                qSub.setCorrect(answer.isCorrect());
            } catch (NonExistentEntityException e) {
                qSub = new QuestionSubmission();
                qSub.set_submission(submission);
                try{
                    qSub.set_question(examService.getQuestionScore(submission.get_exam(),question));
                } catch (NonExistentEntityException e1) {
                    e1.printStackTrace(); // Nunca deve acontecer
                    throw new PersistentException();
                }
                qSub.set_answer(answer);
                qSub.setCorrect(answer.isCorrect());
                submission._questionSubmissions.add(qSub);
            }
        }
        submission.setScore(0);
        for(QuestionSubmission qSub: submission._questionSubmissions.toArray()){
            if(qSub.isCorrect()){
                submission.setScore( submission.getScore() + qSub.get_question().getScore() );
            }
        }
    }

    @Override
    public QuestionSubmission getQuestionSubmission(Submission submission, Question question) throws NonExistentEntityException {
        List<QuestionSubmission> qSubs = Arrays.asList(submission._questionSubmissions.toArray());
        for(QuestionSubmission qSub: qSubs){
            if(qSub.get_question().getID() == question.getID())
                return qSub;
        }
        throw new NonExistentEntityException();
    }

    @Override
    public Submission getSubmissionByID(int ID) throws PersistentException, NonExistentEntityException {
        if(!submissionDAO.exists(ID))
            throw new NonExistentEntityException();
        return submissionDAO.loadSubmissionByORMID(ID);
    }

    @Override
    public boolean exists(int ID) throws PersistentException {
        return submissionDAO.exists(ID);
    }

    @Override
    public boolean exists(Student student, Exam exam) throws PersistentException {
        return submissionDAO.exists(student.getID(), exam.getID());
    }
}
