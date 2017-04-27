package com.laba3.controllers;

import com.laba3.service.UserService;
import com.laba3.service.UserServiceImp;
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
                .getResource("/WEB-INF/log4j.properties"));
    }

    final static Logger logger = Logger.getLogger(LogginServlet.class);

    private static UserService userService = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (userService.authentication(login, password) != null && !userService.authentication(login, password).getLogin().equals("admin")) {
            req.getSession().setAttribute("userLogin", login);
            logger.debug("user: " + login + " logged" );
            resp.sendRedirect(req.getContextPath() + "/listUser");

        } else if (userService.authentication(login, password) != null &&
                userService.authentication(login, password).getLogin().equals("admin")) {

            req.getSession().setAttribute("userLogin", login);
            logger.debug("user: " + login + " logged" );
            resp.sendRedirect(req.getContextPath() + "/admin");
        }
            else
            req.getRequestDispatcher("error.jsp").forward(req,resp);
    }

}
