package service;

import exception.*;
import model.*;
import model.Class;
import org.orm.PersistentException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ExamService {

    boolean exists(int examID) throws PersistentException;
    boolean exists(Group group, String examName) throws PersistentException;
    boolean examContainsQuestion(Exam exam, Question question) throws PersistentException;
    boolean examHasStarted(Exam exam);
    boolean examHasFinished(Exam exam);
    boolean examHasSubmissions(Exam exam) throws PersistentException;
    QuestionScore getQuestionScore(Exam exam, Question question) throws NonExistentEntityException;
    void deleteExam(Exam exam) throws EntityNotRemovableException, PersistentException;
    Exam getExamByID(int examID) throws PersistentException, NonExistentEntityException;
    Exam addExamToGroup(Group group, Exam exam) throws PersistentException, ExistentEntityException;
    Exam updateExam(Exam exam, String name, Long beginDate, Integer duration) throws PersistentException, ExistentEntityException;
    Exam createExam(String name, int minutes, long beginDate, List<Integer> questionIDs, Group group)
            throws InvalidExamException, PersistentException, InvalidQuestionException, ExistentEntityException;

    List<Question> generateExamQuestions(Group group, List<String> categories, List<Integer> difficulties) throws PersistentException, InvalidInputException, InsufficientQuestionsException;
    Question generateExamQuestion(Group group, String category, int difficulty, List<Question> excludedQuestions) throws PersistentException, InsufficientQuestionsException;

    Map<String, Set<Exam>> getExamsByUser(User user);
    Map<String, Set<Exam>> getExamsByClass(Class cl);
    Map<String, Set<Exam>> getExamsByGroup(Group group);
    Map<Student, Score> getExamScores(Exam exam) throws PersistentException, InvalidExamException;
}
