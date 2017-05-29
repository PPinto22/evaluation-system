package dao; /**
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
public abstract class DAOFactory {
	private static DAOFactory _factory = new DAOFactoryImpl();
	
	public static DAOFactory getDAOFactory() {
		return _factory;
	}
	
	public abstract UserDAO getUserDAO();
	public abstract StudentDAO getStudentDAO();
	public abstract TeacherDAO getTeacherDAO();
	public abstract ClassDAO getClassDAO();
	public abstract QuestionDAO getQuestionDAO();
	public abstract AnswerDAO getAnswerDAO();
	public abstract GroupDAO getGroupDAO();
	public abstract ExamDAO getExamDAO();
	public abstract QuestionScoreDAO getQuestionScoreDAO();
	public abstract SubmissionDAO getSubmissionDAO();
	public abstract QuestionSubmissionDAO getQuestionSubmissionDAO();
	public abstract NotificationDAO getNotificationDAO();
	public abstract GroupInvitationDAO getGroupInvitationDAO();
	public abstract GroupStudentDAO getGroupStudentDAO();
}

