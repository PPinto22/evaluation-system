package exception;

public class ExistentEntityException extends Exception {

    public ExistentEntityException() {
        super();
    }

    public ExistentEntityException(String message) {
        super(message);
    }
}
