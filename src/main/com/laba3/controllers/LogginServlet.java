package main.com.laba3.controllers;

import main.com.laba3.service.UserService;
import main.com.laba3.service.UserServiceImp;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by set on 23.04.17.
 */
public class LogginServlet extends HttpServlet{

    static {
        PropertyConfigurator.configure(LogginServlet.class.getClassLoader()
                .getResource("log4j.xml"));
    }

    private static final Logger logger = Logger.getLogger(LogginServlet.class);

    private static UserService userService = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/loggin.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loggin = req.getParameter("loggin");
        String password = req.getParameter("password");

        if (userService.authentication(loggin, password) != null ) {
            req.getSession().setAttribute("userLoggin", loggin);
            logger.debug("user: " + loggin + " logged" );
            resp.sendRedirect(req.getContextPath() + "/listUser");
        }
    }

}
