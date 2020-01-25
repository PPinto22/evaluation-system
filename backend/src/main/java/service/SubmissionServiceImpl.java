package service;

import dao.QuestionSubmissionDAO;
import dao.SubmissionDAO;
import exception.ExistentEntityException;
import exception.InvalidAnswerException;
import exception.InvalidQuestionException;
import exception.NonExistentEntityException;
import model.*;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubmissionServiceImpl implements SubmissionService{

    ExamService examService;
    QuestionService questionService;
    QuestionSubmissionDAO questionSubmissionDAO;
    SubmissionDAO submissionDAO;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }
    @Autowired
    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    public SubmissionServiceImpl(QuestionSubmissionDAO questionSubmissionDAO, SubmissionDAO submissionDAO) {
        this.questionSubmissionDAO = questionSubmissionDAO;
        this.submissionDAO = submissionDAO;
    }

    @Override
    public boolean exists(PersistentSession session, Exam exam) throws PersistentException {
        return submissionDAO.existsExam(session, exam.getID());
    }

    @Override
    public void deleteSubmission(Submission submission) throws PersistentException {
        for(QuestionSubmission qSub: submission._questionSubmissions.toArray()){
            submission._questionSubmissions.remove(qSub);
            questionSubmissionDAO.delete(qSub);
        }

        submissionDAO.deleteAndDissociate(submission);
    }

    @Override
    public Submission getSubmissionByStudentAndExam(PersistentSession session, Student student, Exam exam)
            throws PersistentException, NonExistentEntityException {
        if(!submissionDAO.exists(session, student.getID(), exam.getID()))
            throw new NonExistentEntityException();

        return submissionDAO.loadSubmissionByStudentAndExam(session, student.getID(), exam.getID());
    }

    @Override
    public Submission updateSubmission(PersistentSession session, Submission submission, Map<Question, Answer> answers)
            throws InvalidAnswerException, PersistentException, InvalidQuestionException {
        addAnswersToSubmission(session, submission, answers);
        submissionDAO.save(submission);
        return submission;
    }

    @Override
    public Submission submit(PersistentSession session, Student student, Exam exam, Map<Question, Answer> answers)
            throws ExistentEntityException, PersistentException, InvalidQuestionException, InvalidAnswerException {
        if(exists(session, student, exam))
            throw new ExistentEntityException();

        Submission submission = new Submission();
        submission.set_student(student);
        submission.set_exam(exam);
        addAnswersToSubmission(session, submission, answers);
        submissionDAO.save(submission);
        return submission;
    }

    private void addAnswersToSubmission(PersistentSession session, Submission submission, Map<Question, Answer> answers)
            throws InvalidQuestionException, InvalidAnswerException, PersistentException {
        for(Question question: answers.keySet()){
            Answer answer = answers.get(question);
            if(!examService.examContainsQuestion(session, submission.get_exam(), question))
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
            if(qSub.get_question().get_question().getID() == question.getID())
                return qSub;
        }
        throw new NonExistentEntityException();
    }

    @Override
    public Submission getSubmissionByID(PersistentSession session, int ID) throws PersistentException, NonExistentEntityException {
        if(!submissionDAO.exists(session, ID))
            throw new NonExistentEntityException();
        return submissionDAO.loadSubmissionByORMID(session, ID);
    }

    @Override
    public boolean exists(PersistentSession session, int ID) throws PersistentException {
        return submissionDAO.exists(session, ID);
    }

    @Override
    public boolean exists(PersistentSession session, Student student, Exam exam) throws PersistentException {
        return submissionDAO.exists(session, student.getID(), exam.getID());
    }
}
