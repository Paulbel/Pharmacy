package by.pharmacy.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Validator {
    protected boolean checkParameter(String parameter, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(parameter);
        return matcher.matches();
    }

    public static boolean validateId(int id) {
        return id > 0;
    }

    public static boolean paginationCheck(int number, int offset) {
        return number > 0 && offset >= 0;
    }

    public abstract <T> boolean validate(T obj);
}
