/**
 * Licensee: Universidade do Minho
 * License Type: Academic
 */
import org.orm.*;
public class ListClassesData {
	private static final int ROW_COUNT = 100;
	
	public void listTestData() throws PersistentException {
		System.out.println("Listing User...");
		User[] users = User.listUserByQuery(null, null);
		int length = Math.min(users.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(users[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Student...");
		Student[] students = Student.listStudentByQuery(null, null);
		length = Math.min(students.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(students[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Teacher...");
		Teacher[] teachers = Teacher.listTeacherByQuery(null, null);
		length = Math.min(teachers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(teachers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Class...");
		Class[] classes = Class.listClassByQuery(null, null);
		length = Math.min(classes.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(classes[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Question...");
		Question[] questions = Question.listQuestionByQuery(null, null);
		length = Math.min(questions.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(questions[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing User by Criteria...");
		UserCriteria userCriteria = new UserCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//userCriteria.attribute.eq();
		userCriteria.setMaxResults(ROW_COUNT);
		User[] users = userCriteria.listUser();
		int length =users== null ? 0 : Math.min(users.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(users[i]);
		}
		System.out.println(length + " User record(s) retrieved."); 
		
		System.out.println("Listing Student by Criteria...");
		StudentCriteria studentCriteria = new StudentCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//studentCriteria.attribute.eq();
		studentCriteria.setMaxResults(ROW_COUNT);
		Student[] students = studentCriteria.listStudent();
		length =students== null ? 0 : Math.min(students.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(students[i]);
		}
		System.out.println(length + " Student record(s) retrieved."); 
		
		System.out.println("Listing Teacher by Criteria...");
		TeacherCriteria teacherCriteria = new TeacherCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//teacherCriteria.attribute.eq();
		teacherCriteria.setMaxResults(ROW_COUNT);
		Teacher[] teachers = teacherCriteria.listTeacher();
		length =teachers== null ? 0 : Math.min(teachers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(teachers[i]);
		}
		System.out.println(length + " Teacher record(s) retrieved."); 
		
		System.out.println("Listing Class by Criteria...");
		ClassCriteria class1Criteria = new ClassCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//class1Criteria.attribute.eq();
		class1Criteria.setMaxResults(ROW_COUNT);
		Class[] classes = class1Criteria.listClass();
		length =classes== null ? 0 : Math.min(classes.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(classes[i]);
		}
		System.out.println(length + " Class record(s) retrieved."); 
		
		System.out.println("Listing Question by Criteria...");
		QuestionCriteria questionCriteria = new QuestionCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//questionCriteria.ID.eq();
		questionCriteria.setMaxResults(ROW_COUNT);
		Question[] questions = questionCriteria.listQuestion();
		length =questions== null ? 0 : Math.min(questions.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(questions[i]);
		}
		System.out.println(length + " Question record(s) retrieved."); 
		
	}
	
	public static void main(String[] args) {
		try {
			ListClassesData listClassesData = new ListClassesData();
			try {
				listClassesData.listTestData();
				//listClassesData.listByCriteria();
			}
			finally {
				ClassesPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
