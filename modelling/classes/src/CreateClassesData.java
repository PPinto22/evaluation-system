/**
 * Licensee: Universidade do Minho
 * License Type: Academic
 */
import org.orm.*;
public class CreateClassesData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = ClassesPersistentManager.instance().getSession().beginTransaction();
		try {
			User user = User.createUser();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : ID
			user.save();
			Student student = Student.createStudent();
			// Initialize the properties of the persistent object here
			student.save();
			Teacher teacher = Teacher.createTeacher();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : _classes
			teacher.save();
			Class class1 = Class.createClass();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : _question, _teacher, ID
			class1.save();
			Question question = Question.createQuestion();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : _class
			question.save();
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			CreateClassesData createClassesData = new CreateClassesData();
			try {
				createClassesData.createTestData();
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
