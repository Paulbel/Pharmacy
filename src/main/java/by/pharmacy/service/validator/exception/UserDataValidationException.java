package by.pharmacy.service.validator.exception;

import by.pharmacy.service.exception.ServiceException;

public class UserDataValidationException extends ServiceException{
    private static final long serialVersionUID = -6291740285955080561L;

    public UserDataValidationException() {
    }

    public UserDataValidationException(String message) {
        super(message);
    }

    public UserDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDataValidationException(Throwable cause) {
        super(cause);
    }
}
