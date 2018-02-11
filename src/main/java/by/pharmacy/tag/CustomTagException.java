package by.pharmacy.tag;

public class CustomTagException extends RuntimeException {
    private static final long serialVersionUID = -6381794357141095843L;

    public CustomTagException() {
    }

    public CustomTagException(String message) {
        super(message);
    }

    public CustomTagException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomTagException(Throwable cause) {
        super(cause);
    }
}
