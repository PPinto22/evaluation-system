package exception;

public class ExistentUserException extends Exception {

    public ExistentUserException() {
        super();
    }

    public ExistentUserException(String message) {
        super(message);
    }
}
