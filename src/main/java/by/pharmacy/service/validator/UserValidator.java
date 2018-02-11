package by.pharmacy.service.validator;

import by.pharmacy.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private final static String NAME_REGEX = "[\\w\\dа-яА-Я]{1,20}";
    private final static String LOGIN_REGEX = "[\\w\\d]{4,20}";
    private final static String SURNAME_REGEX = "[\\w\\d]{4,20}";
    private final static String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private final static String PHONE_REGEX = "\\+?\\d{0,19}";


    public static boolean validateUser(User user) {
        return checkParameter(user.getName(), NAME_REGEX) &&
                checkParameter(user.getLogin(), LOGIN_REGEX) &&
                checkParameter(user.getSurname(), SURNAME_REGEX) &&
                checkParameter(user.getEmail(), EMAIL_REGEX) &&
                checkParameter(user.getPhoneNumber(), PHONE_REGEX);
    }


    private static boolean checkParameter(String parameter, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(parameter);
        return matcher.matches();
    }

}
