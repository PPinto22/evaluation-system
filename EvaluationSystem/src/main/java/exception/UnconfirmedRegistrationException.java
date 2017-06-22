package exception;

public class UnconfirmedEmailException extends Exception {

    public UnconfirmedEmailException() {
        super();
    }

    public UnconfirmedEmailException(String message) {
        super(message);
    }
}
