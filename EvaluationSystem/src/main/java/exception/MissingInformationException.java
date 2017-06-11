package exception;

public class MissingInformationException extends Exception {

    public MissingInformationException() {
        super();
    }

    public MissingInformationException(String message) {
        super(message);
    }
}
