package by.vistar.comeco.web.controllers;

import by.vistar.comeco.controler.constants.ConstantsParameters;
import by.vistar.comeco.db.DbConnection;
import by.vistar.comeco.entity.autor.User;
import by.vistar.comeco.services.autor.ServiceUser;
import by.vistar.comeco.web.controllers.validation.Validation;
import org.junit.Test;

import static by.vistar.comeco.controler.constants.ConstantsAttributes.FLAG_ERRORS;
import static by.vistar.comeco.controler.constants.ConstantsErrors.ERROR_EQUALS_PASSWORD;
import static by.vistar.comeco.controler.constants.ConstantsErrors.ERROR_LOGIN_OR_PASS;
import static org.junit.Assert.*;

public class NewUserControllerTest {

    @Test
    public void execute() {
        String login = "login@login.com";
        String password1 = "password";
        String password2 = "password";
        if (login != null && password1 != null && password1.equals(password2)) {
            if (Validation.validationLogin(login) && Validation.validationPassword(password1)) {
                User user = new User();
                user.setLogin(login);
                user.setE_mail(login);
                user.setPackage_id(1L);
                user.setPassword(password1);
                user = new ServiceUser(DbConnection.getConnection()).add(user);
                assertNotNull(user);
            }
        }
    }
}