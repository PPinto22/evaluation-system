package exception;

public class InvalidAuthenticationException extends Exception {

    public InvalidAuthenticationException() {
        super();
    }

    public InvalidAuthenticationException(String message) {
        super(message);
    }
}
