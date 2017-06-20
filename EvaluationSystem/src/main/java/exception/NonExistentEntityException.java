package exception;

public class NonExistentEntityException extends Exception {

    public NonExistentEntityException() {
        super();
    }

    public NonExistentEntityException(String message) {
        super(message);
    }
}
