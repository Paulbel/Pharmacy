package by.pharmacy.service.validator;

import by.pharmacy.service.validator.exception.UserDataValidationException;

public interface UserDataValidator {
    String EMAIL_REGEXP = "^[A-Za-z0-9+_.-]+@(.+)$";
    String LOGIN_REGEXP = "^[a-zA-Z0-9_]{3,}$";
    int MIN_PASSWORD_LENGTH = 4;
    int MAX_PASSWORD_LENGTH = 64;

    boolean checkLogin(String login);
    boolean checkPassword(String password);
    boolean checkEMail(String email);

    default boolean validateCredentials (String login, String password) throws UserDataValidationException{
        if(!checkLogin(login)){
            throw new UserDataValidationException("Incorrect login");
        }
        if(!checkPassword(password)){
            throw new UserDataValidationException("Invalid password");
        }
        return true;
    }
    default boolean validateCredentials (String login, String password, String eMail) throws UserDataValidationException {
        if(!checkLogin(login)){
            throw new UserDataValidationException("Incorrect login");
        }
        if(!checkEMail(eMail)){
            throw  new UserDataValidationException("Invalid email");
        }
        if(!checkPassword(password)){
            throw new UserDataValidationException("Invalid password");
        }
        return true;
    }
}