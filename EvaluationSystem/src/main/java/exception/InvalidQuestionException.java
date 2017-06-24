package exception;

public class InvalidQuestionException extends Exception {

    public InvalidQuestionException() {
        super();
    }

    public InvalidQuestionException(String message) {
        super(message);
    }
}
