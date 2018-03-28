package exception;

public class InvalidMoveException extends Exception {
    private String errorMessage;

    public InvalidMoveException(String message) {
        super(message);
        this.errorMessage = errorMessage;
    }
}
