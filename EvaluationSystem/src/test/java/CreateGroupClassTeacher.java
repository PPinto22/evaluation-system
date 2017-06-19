//import dao.UserDAO;
//import dao.UserDAOImpl;
//import model.Group;
//import model.Teacher;
//import model.Class;
//import org.orm.PersistentException;
//
//public class CreateGroupClassTeacher {
//
//    public static void main(String[] args) {
//        Teacher teacher = new Teacher();
//        teacher.setEmail("teacher1@gmail.com");
//        teacher.setFirstName("Gervásio");
//        teacher.setLastName("Silva");
//        teacher.setPassword("password");
//
//        Class cl = new Class();
//        cl.setName("Arquiteturas Aplicacionais");
//        cl.setAbbreviation("AA");
//        cl.set_teacher(teacher);
//
//        Group group = new Group();
//        group.setName("2016/2017");
//        group.set_class(cl);
//
//        UserDAO userDAO = new UserDAOImpl();
//        try {
//            userDAO.save(teacher);
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//    }
//}
