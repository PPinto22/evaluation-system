/**
 * Licensee: Universidade do Minho
 * License Type: Academic
 */
import org.orm.*;
public class RetrieveAndUpdateClassesData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = ClassesPersistentManager.instance().getSession().beginTransaction();
		try {
			User user = User.loadUserByQuery(null, null);
			// Update the properties of the persistent object
			user.save();
			Student student = Student.loadStudentByQuery(null, null);
			// Update the properties of the persistent object
			student.save();
			Teacher teacher = Teacher.loadTeacherByQuery(null, null);
			// Update the properties of the persistent object
			teacher.save();
			Class class1 = Class.loadClassByQuery(null, null);
			// Update the properties of the persistent object
			class1.save();
			Question question = Question.loadQuestionByQuery(null, null);
			// Update the properties of the persistent object
			question.save();
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving User by UserCriteria");
		UserCriteria userCriteria = new UserCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//userCriteria.attribute.eq();
		System.out.println(userCriteria.uniqueUser());
		
		System.out.println("Retrieving Student by StudentCriteria");
		StudentCriteria studentCriteria = new StudentCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//studentCriteria.attribute.eq();
		System.out.println(studentCriteria.uniqueStudent());
		
		System.out.println("Retrieving Teacher by TeacherCriteria");
		TeacherCriteria teacherCriteria = new TeacherCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//teacherCriteria.attribute.eq();
		System.out.println(teacherCriteria.uniqueTeacher());
		
		System.out.println("Retrieving Class by ClassCriteria");
		ClassCriteria class1Criteria = new ClassCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//class1Criteria.attribute.eq();
		System.out.println(class1Criteria.uniqueClass());
		
		System.out.println("Retrieving Question by QuestionCriteria");
		QuestionCriteria questionCriteria = new QuestionCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//questionCriteria.ID.eq();
		System.out.println(questionCriteria.uniqueQuestion());
		
	}
	
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateClassesData retrieveAndUpdateClassesData = new RetrieveAndUpdateClassesData();
			try {
				retrieveAndUpdateClassesData.retrieveAndUpdateTestData();
				//retrieveAndUpdateClassesData.retrieveByCriteria();
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
