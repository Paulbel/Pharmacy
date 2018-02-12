package by.pharmacy.service.exception;

public class InvalidDataException extends ServiceException {
    private static final long serialVersionUID = -8618607268263965435L;

    public InvalidDataException() {
    }

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDataException(Throwable cause) {
        super(cause);
    }
}
