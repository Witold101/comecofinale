package by.vistar.comeco.web.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {
    String INDEX_PAGE = "/index.jsp";
    void execute(HttpServletRequest req, HttpServletResponse pesp)
        throws IOException,ServletException;
}
