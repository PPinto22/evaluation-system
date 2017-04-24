/**
 * Licensee: Universidade do Minho
 * License Type: Academic
 */
import org.orm.*;
public class CreateClassesDatabaseSchema {
	public static void main(String[] args) {
		try {
			ORMDatabaseInitiator.createSchema(ClassesPersistentManager.instance());
			ClassesPersistentManager.instance().disposePersistentManager();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
