package exception;

public class AlreadyOccupiedTileException extends Exception {
    private String errorMessage;

    public AlreadyOccupiedTileException(String message) {
        super(message);
        this.errorMessage = errorMessage;
    }
}
