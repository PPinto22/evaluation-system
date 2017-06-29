package exception;

public class NoSuchParamException extends Exception {

    public NoSuchParamException() {
        super();
    }

    public NoSuchParamException(String message) {
        super(message);
    }
}
