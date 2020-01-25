package exception;

public class UserNotInGroupException extends Exception {

    public UserNotInGroupException() {
        super();
    }

    public UserNotInGroupException(String message) {
        super(message);
    }
}
