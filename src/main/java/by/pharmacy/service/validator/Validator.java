package by.pharmacy.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Validator {
    protected boolean checkParameter(String parameter, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(parameter);
        return matcher.matches();
    }
}
