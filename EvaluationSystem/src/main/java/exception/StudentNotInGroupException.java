package exception;

public class StudentNotInGroupException extends Exception {

    public StudentNotInGroupException() {
        super();
    }

    public StudentNotInGroupException(String message) {
        super(message);
    }
}
