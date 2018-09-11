package by.vistar.comeco.web.servlets;

import by.vistar.comeco.controler.constants.ConstantsParameters;
import by.vistar.comeco.web.controllers.LoginController;
import by.vistar.comeco.web.controllers.NewUserController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vistar.comeco.controler.constants.ConstantsAttributes.FLAG_ERRORS;
import static by.vistar.comeco.controler.constants.ConstantsAttributes.FLAG_REQUEST;
import static by.vistar.comeco.controler.constants.ConstantsParameters.*;


/**
 * Сервлет управляет вводом логина и пароля в базу и устанавливает в сессию данные объекта User черз контроллер.
 * и так же устанавливает атрибут ошибки ввода пароляи логина.
 */

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(FLAG_ERRORS,"");
        if (LOGIN_NEW.equals(req.getParameter(FLAG_REQUEST))) {
            new NewUserController().execute(req, resp);
        } else if (LOGIN.equals(req.getParameter(FLAG_REQUEST))) {
            new LoginController().execute(req,resp);
        }
        if (req.getAttribute(FLAG_ERRORS)!= null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
