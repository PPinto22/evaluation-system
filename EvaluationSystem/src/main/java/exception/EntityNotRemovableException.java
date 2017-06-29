package exception;

public class UserNotRemovableException extends Exception {

    public UserNotRemovableException() {
        super();
    }

    public UserNotRemovableException(String message) {
        super(message);
    }
}
