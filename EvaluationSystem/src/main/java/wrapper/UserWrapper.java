package wrapper;

import model.User;

public class UserWrapper{

    private int ID;
    private String email;
    private String firstName;
    private String lastName;
    private String type;
    private boolean active;

    public UserWrapper() {
    }

    public UserWrapper(User user){
        this.setEmail(user.getEmail());
        this.setID(user.getID());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setActive(user.isRegistered() && !user.isDeleted());
        switch (user.getClass().getSimpleName()){
            case "Student":
                this.type = "student";
                break;
            case "Teacher":
                this.type = "teacher";
                break;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
