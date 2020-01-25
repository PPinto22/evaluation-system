package exception;

public class InvalidUserTypeException extends Exception {

    public InvalidUserTypeException() {
        super();
    }

    public InvalidUserTypeException(String message) {
        super(message);
    }
}
