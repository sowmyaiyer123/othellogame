package exception;

public class IncorrectTileAddressException extends Exception {
    private String errorMessage;

    public IncorrectTileAddressException(String message) {
        super(message);
        this.errorMessage = errorMessage;
    }
}
