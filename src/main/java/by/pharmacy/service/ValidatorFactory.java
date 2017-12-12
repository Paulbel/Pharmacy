package by.pharmacy.service;

import by.pharmacy.validator.UserDataValidator;
import by.pharmacy.validator.impl.UserDataValidatorImpl;

public class ValidatorFactory {
    public static final ValidatorFactory instance = new ValidatorFactory();
    private UserDataValidator userDataValidator= new UserDataValidatorImpl();


    public UserDataValidator getUserDataValidator() {
        return userDataValidator;
    }

    public static ValidatorFactory getInstance() {
        return instance;
    }

    private ValidatorFactory() {
    }
}
