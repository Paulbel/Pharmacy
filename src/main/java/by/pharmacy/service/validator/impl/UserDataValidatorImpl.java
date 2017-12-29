package by.pharmacy.service.validator.impl;

import by.pharmacy.service.validator.UserDataValidator;

public class UserDataValidatorImpl implements UserDataValidator {
    @Override
    public boolean checkLogin(String login) {
        return false;
    }

    @Override
    public boolean checkPassword(String password) {
        return false;
    }

    @Override
    public boolean checkEMail(String email) {
        return false;
    }
}
