package service;

import model.Student;
import model.Teacher;
import model.User;

public interface UserService {

    // TODO - Estes métodos não são definitivos. Só para experimentar coisas
    public void addUser(User user);
    public Student[] getStudents();
    public Teacher[] getTeachers();
}
