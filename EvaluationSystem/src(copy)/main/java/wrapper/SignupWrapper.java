package wrapper;

public class SignupWrapper extends LoginWrapper {

    private String type;
    private String firstName;
    private String lastName;

    public SignupWrapper(){}

    public SignupWrapper(String type, String firstName, String lastName) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public SignupWrapper(String email, String password, String type, String firstName, String lastName) {
        super(email, password);
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
