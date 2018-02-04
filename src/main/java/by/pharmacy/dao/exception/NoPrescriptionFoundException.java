package by.pharmacy.dao.exception;

public class NoPrescriptionFoundException extends DAOException {
    private static final long serialVersionUID = -4405040614452956051L;

    public NoPrescriptionFoundException() {
        super();
    }

    public NoPrescriptionFoundException(String message) {
        super(message);
    }

    public NoPrescriptionFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPrescriptionFoundException(Throwable cause) {
        super(cause);
    }
}
