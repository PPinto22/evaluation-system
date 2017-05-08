package model; /**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: Universidade do Minho
 * License Type: Academic
 */
public class DAOFactoryImpl extends DAOFactory {
	private UserDAO _userDAO = new UserDAOImpl();
	public UserDAO getUserDAO() {
		return _userDAO;
	}
	
	private StudentDAO _studentDAO = new StudentDAOImpl();
	public StudentDAO getStudentDAO() {
		return _studentDAO;
	}
	
	private TeacherDAO _teacherDAO = new TeacherDAOImpl();
	public TeacherDAO getTeacherDAO() {
		return _teacherDAO;
	}
	
	private ClassDAO _classDAO = new ClassDAOImpl();
	public ClassDAO getClassDAO() {
		return _classDAO;
	}
	
	private QuestionDAO _questionDAO = new QuestionDAOImpl();
	public QuestionDAO getQuestionDAO() {
		return _questionDAO;
	}
	
	private AnswerDAO _answerDAO = new AnswerDAOImpl();
	public AnswerDAO getAnswerDAO() {
		return _answerDAO;
	}
	
	private GroupDAO _groupDAO = new GroupDAOImpl();
	public GroupDAO getGroupDAO() {
		return _groupDAO;
	}
	
	private ExamDAO _examDAO = new ExamDAOImpl();
	public ExamDAO getExamDAO() {
		return _examDAO;
	}
	
	private QuestionScoreDAO _questionScoreDAO = new QuestionScoreDAOImpl();
	public QuestionScoreDAO getQuestionScoreDAO() {
		return _questionScoreDAO;
	}
	
	private SubmissionDAO _submissionDAO = new SubmissionDAOImpl();
	public SubmissionDAO getSubmissionDAO() {
		return _submissionDAO;
	}
	
	private QuestionSubmissionDAO _questionSubmissionDAO = new QuestionSubmissionDAOImpl();
	public QuestionSubmissionDAO getQuestionSubmissionDAO() {
		return _questionSubmissionDAO;
	}
	
	private NotificationDAO _notificationDAO = new NotificationDAOImpl();
	public NotificationDAO getNotificationDAO() {
		return _notificationDAO;
	}
	
	private GroupInvitationDAO _groupInvitationDAO = new GroupInvitationDAOImpl();
	public GroupInvitationDAO getGroupInvitationDAO() {
		return _groupInvitationDAO;
	}
	
	private GroupStudentDAO _groupStudentDAO = new GroupStudentDAOImpl();
	public GroupStudentDAO getGroupStudentDAO() {
		return _groupStudentDAO;
	}
	
}

