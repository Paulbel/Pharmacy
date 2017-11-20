package by.optics.dao.exception;

public class NoUserFoundException extends DAOException {
    public NoUserFoundException() {
    }

    public NoUserFoundException(String message) {
        super(message);
    }

    public NoUserFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoUserFoundException(Throwable cause) {
        super(cause);
    }
}
