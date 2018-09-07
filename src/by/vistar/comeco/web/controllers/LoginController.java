package by.vistar.comeco.web.controllers;

import by.vistar.comeco.db.DbConnection;
import com.cameco.entity.User;
import com.cameco.services.ServiceUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Контроллер получает данные о высланных данных о логине и пароли, сравнивает их и в случае положительного
 * результата прописывает в сессиию объект User.
 */

public class LoginController implements Controller {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && password != null) {
            User user = new ServiceUser(DbConnection.getConnection()).getUserFromLoginAndPassword(login, password);
            if (user.getId() != null) {
                req.getSession().setAttribute("user", user);
            }
        }
    }
}
