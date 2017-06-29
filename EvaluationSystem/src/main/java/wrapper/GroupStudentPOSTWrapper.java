package wrapper;


import com.fasterxml.jackson.annotation.JsonInclude;
import model.GroupStudent;
import model.Student;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupStudentPOSTWrapper {

    private String email;
    private UserWrapper user;
    private String message;

    public GroupStudentPOSTWrapper(){}

    public GroupStudentPOSTWrapper(GroupStudent groupStudent){
        Student student = groupStudent.get_student();
        this.user = new UserWrapper(student);
        this.email = student.getEmail();
    }

    public GroupStudentPOSTWrapper(String email, String message){
        this.email = email;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserWrapper getUser() {
        return user;
    }

    public void setUser(UserWrapper user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
