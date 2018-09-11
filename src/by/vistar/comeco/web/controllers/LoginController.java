package by.vistar.comeco.web.controllers;

import by.vistar.comeco.controler.constants.ConstantsAttributes;
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
import static by.vistar.comeco.controler.constants.ConstantsErrors.ERROR_LOGIN_OR_PASS;

/**
 * Контроллер получает данные о высланных данных о логине и пароли, сравнивает их и в случае положительного
 * результата прописывает в сессиию объект User.
 */

public class LoginController implements Controller {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter(ConstantsParameters.LOGIN_NEW);
        String password = req.getParameter(ConstantsParameters.PASSWORD);
        req.setAttribute(FLAG_ERRORS,ERROR_LOGIN_OR_PASS);
        if (login != null && password != null) {
            if (Validation.validationLogin(login) && Validation.validationPassword(password)) {
                User user = new ServiceUser(DbConnection.getConnection()).getUserFromLoginAndPassword(login, password);
                if (user.getId() != null) {
                    req.getSession().setAttribute(ConstantsParameters.USER, user);
                    req.setAttribute(FLAG_ERRORS,null);
                }
            }
        }
    }
}
