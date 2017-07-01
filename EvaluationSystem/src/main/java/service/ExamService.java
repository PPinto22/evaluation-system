package service;

import exception.*;
import model.*;
import model.Class;
import org.orm.PersistentException;
import org.orm.PersistentSession;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ExamService {

    boolean exists(PersistentSession session, int examID) throws PersistentException;
    boolean exists(PersistentSession session, Group group, String examName) throws PersistentException;
    boolean examContainsQuestion(PersistentSession session, Exam exam, Question question) throws PersistentException;
    boolean examHasStarted(Exam exam);
    boolean examHasFinished(Exam exam);
    boolean examHasSubmissions(PersistentSession session, Exam exam) throws PersistentException;
    QuestionScore getQuestionScore(Exam exam, Question question) throws NonExistentEntityException;
    void deleteExam(PersistentSession session, Exam exam) throws EntityNotRemovableException, PersistentException;
    Exam getExamByID(PersistentSession session, int examID) throws PersistentException, NonExistentEntityException;
    Exam addExamToGroup(PersistentSession session, Group group, Exam exam) throws PersistentException, ExistentEntityException;
    Exam updateExam(PersistentSession session, Exam exam, String name, Long beginDate, Integer duration) throws PersistentException, ExistentEntityException;
    Exam createExam(PersistentSession session, String name, int minutes, long beginDate, List<Integer> questionIDs, Group group)
            throws InvalidExamException, PersistentException, InvalidQuestionException, ExistentEntityException;

    List<Question> generateExamQuestions(PersistentSession session, Group group, List<String> categories, List<Integer> difficulties) throws PersistentException, InvalidInputException, InsufficientQuestionsException;
    Question generateExamQuestion(PersistentSession session, Group group, String category, int difficulty, List<Question> excludedQuestions) throws PersistentException, InsufficientQuestionsException;

    Map<String, Set<Exam>> getExamsByUser(User user);
    Map<String, Set<Exam>> getExamsByClass(Class cl);
    Map<String, Set<Exam>> getExamsByGroup(Group group);
    Map<Student, Score> getExamScores(PersistentSession session, Exam exam) throws PersistentException, InvalidExamException;

    Set<Exam> getOngoingExamsByUser(User user);
    Set<Exam> getOngoingExamsByGroup(Group group);
}
