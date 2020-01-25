package exception;

public class UnconfirmedRegistrationException extends Exception {

    public UnconfirmedRegistrationException() {
        super();
    }

    public UnconfirmedRegistrationException(String message) {
        super(message);
    }
}
