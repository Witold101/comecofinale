package by.vistar.comeco.web.servlets;

import by.vistar.comeco.controler.InitDropTables;
import by.vistar.comeco.controler.Setup;
import by.vistar.comeco.db.DbConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class MainServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("<p><span style='color':blue;'>Число посещений адреса = </span></p>");
        //if (!Setup.flagFirstLogin) {
        new InitDropTables(DbConnection.getConnection()).initUserTable();
        //    Setup.flagFirstLogin = true;
        //}
    }
}
