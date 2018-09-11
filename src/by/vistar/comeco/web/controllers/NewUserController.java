package by.vistar.comeco.web.controllers;

import by.vistar.comeco.controler.constants.ConstantsParameters;
import by.vistar.comeco.db.DbConnection;
import by.vistar.comeco.entity.autor.User;
import by.vistar.comeco.services.autor.ServiceUser;
import by.vistar.comeco.web.controllers.validation.Validation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vistar.comeco.controler.constants.ConstantsAttributes.FLAG_ERRORS;
import static by.vistar.comeco.controler.constants.ConstantsErrors.ERROR_EQUALS_PASSWORD;
import static by.vistar.comeco.controler.constants.ConstantsErrors.ERROR_LOGIN_OR_PASS;

/**
 * Объект получает данные из формы, валидирует данные для Логина и Пароли. В случае корректных данных
 * создает нового пользователя и вносит его в базу данных, в сесию заносит объект User
 */

public class NewUserController implements Controller {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter(ConstantsParameters.EMAIL);
        String password1 = req.getParameter(ConstantsParameters.PASSWORD_1);
        String password2 = req.getParameter(ConstantsParameters.PASSWORD_2);
        if (login != null && password1 != null && password1.equals(password2)) {
            req.setAttribute(FLAG_ERRORS, ERROR_LOGIN_OR_PASS);
            if (Validation.validationLogin(login) && Validation.validationPassword(password1)) {
                User user = new User();
                user.setLogin(login);
                user.setE_mail(login);
                user.setPackage_id(1L);
                user.setPassword(password1);
                ServiceUser serviceUser = new ServiceUser(DbConnection.getConnection());
                user = serviceUser.add(user);
                if (user.getId() != null) {
                    req.getSession().setAttribute(ConstantsParameters.USER, user);
                    req.setAttribute(FLAG_ERRORS, null);
                }
            }
        } else {
            req.setAttribute(FLAG_ERRORS, ERROR_EQUALS_PASSWORD);
        }
    }
}
