package by.pharmacy.dao.connectionPool.exception;

import by.pharmacy.dao.exception.DAOException;

public class ConnectionPoolException extends DAOException {
    private static final long serialVersionUID = 8430939004103953416L;

    public ConnectionPoolException() {
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
