package exception;

public class InvalidAnswerException extends Exception {

    public InvalidAnswerException() {
        super();
    }

    public InvalidAnswerException(String message) {
        super(message);
    }
}
