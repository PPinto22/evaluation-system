package exception;

public class InsufficientQuestionsException extends Exception {

    public InsufficientQuestionsException() {
        super();
    }

    public InsufficientQuestionsException(String message) {
        super(message);
    }
}
