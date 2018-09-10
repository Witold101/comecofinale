package by.vistar.comeco.web.controllers;

import by.vistar.comeco.controler.constants.ConstantsParameters;
import by.vistar.comeco.db.DbConnection;
import by.vistar.comeco.web.controllers.validation.Validation;
import com.cameco.entity.User;
import com.cameco.services.ServiceUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vistar.comeco.controler.constants.ConstantsAttributes.FLAG_ERRORS;
import static by.vistar.comeco.controler.constants.ConstantsErrors.ERROR_LOGIN_OR_PASS;

/**
 * Объект получает данные из формы, валидирует данные для Логина и Пароли. В случае корректных данных
 * создает нового пользователя и вносит его в базу данных, в сесию заносит объект User
 */

public class NewUserController implements Controller {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter(ConstantsParameters.LOGIN_NEW);
        String password = req.getParameter(ConstantsParameters.PASSWORD);
        req.setAttribute(FLAG_ERRORS,ERROR_LOGIN_OR_PASS);
        if (login != null && password != null) {
            if (Validation.validationLogin(login) && Validation.validationPassword(password)) {
                User user = new User();
                user.setLogin(login);
                user.setE_mail(login);
                user.setPackage_id(1L);
                user.setPassword(password);
                user = new ServiceUser(DbConnection.getConnection()).add(user);
                if (user.getId() != null) {
                    req.getSession().setAttribute(ConstantsParameters.USER, user);
                    req.setAttribute(FLAG_ERRORS,null);
                }
            }
        }
    }
}
