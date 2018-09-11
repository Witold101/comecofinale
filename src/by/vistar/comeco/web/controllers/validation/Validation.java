package by.vistar.comeco.web.controllers.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.vistar.comeco.db.autor.DbConstantsAutor.*;

public class Validation {

    public static Boolean validationLogin(String login) {
        Boolean result = false;
        if (login.length() <= MAX_LENGTH_LOGIN) {
            final String EMAIL_PATTERN =
                    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            Pattern pattern;
            Matcher matcher;
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(login);
            result = matcher.matches();
        } else {
            result = false;
        }
        return result;
    }


    public static Boolean validationPassword(String pass) {
        return pass.length() <= MAX_LENGTH_PASSWORD;
    }
}
