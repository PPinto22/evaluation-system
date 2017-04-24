/**
 * Licensee: Universidade do Minho
 * License Type: Academic
 */
import org.orm.*;
public class DeleteClassesData {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = ClassesPersistentManager.instance().getSession().beginTransaction();
		try {
			User user = User.loadUserByQuery(null, null);
			user.delete();
			Student student = Student.loadStudentByQuery(null, null);
			student.delete();
			Teacher teacher = Teacher.loadTeacherByQuery(null, null);
			teacher.delete();
			Class class1 = Class.loadClassByQuery(null, null);
			class1.delete();
			Question question = Question.loadQuestionByQuery(null, null);
			question.delete();
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			DeleteClassesData deleteClassesData = new DeleteClassesData();
			try {
				deleteClassesData.deleteTestData();
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
