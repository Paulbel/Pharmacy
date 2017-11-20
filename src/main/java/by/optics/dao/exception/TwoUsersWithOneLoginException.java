package by.optics.dao.exception;

import by.optics.dao.impl.SQLUserDAO;

public class TwoUsersWithOneLoginException extends DAOException {

    public TwoUsersWithOneLoginException() {
    }

    public TwoUsersWithOneLoginException(String message) {
        super(message);
    }

    public TwoUsersWithOneLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public TwoUsersWithOneLoginException(Throwable cause) {
        super(cause);
    }
}
