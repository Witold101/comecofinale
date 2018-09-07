package by.vistar.comeco.web.servlets;

import by.vistar.comeco.controler.InitDropTables;
import by.vistar.comeco.controler.Setup;
import by.vistar.comeco.db.DbConnection;
import com.cameco.entity.PackageSoft;
import com.cameco.services.ServicePackageSoft;
import com.cameco.services.ServiceUser;

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
        writer.write("<p><span style='color':blue;'>Число посещений адреса ="
                + Setup.flagFirstLogin.toString()+" </span></p>");
        if (!Setup.flagFirstLogin) {
            PackageSoft packageSoft = new PackageSoft();
            packageSoft.setKey(0);
            packageSoft.setName("Simple");
            packageSoft.setInfo("Simple package.");
            new InitDropTables(DbConnection.getConnection()).initUserTable();
            new ServicePackageSoft(DbConnection.getConnection()).add(packageSoft);
            Setup.flagFirstLogin = true;
        }
    }
}
