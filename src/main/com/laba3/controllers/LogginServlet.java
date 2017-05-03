package com.laba3.controllers;

import com.laba3.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by set on 23.04.17.
 */
public class LogginServlet extends HttpServlet{

//    static {
//        PropertyConfigurator.configure(LogginServlet.class.getClassLoader()
//                .getResource("log4j.properties"));
//    }

    final static Logger logger = Logger.getLogger(LogginServlet.class);
    //вместо этой строки делаешь
    //private static UserService userService = new UserServiceImp();
    //эоо бддет автосвязывание
    //еще тебе надо фалйы конфиги сделать для спрннга
    //сначала сделай протто ioc, а потом webmvc делай
    @Autowired
    private  UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.
                processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession httpSession = req.getSession();
        String action = req.getParameter("action");

        if(action != null && !action.isEmpty()){
            if(action.equals("exit")){
                httpSession.invalidate();
                req.getRequestDispatcher("/home.jsp").forward(req,resp);
            }
        } else {
            if (userService.authentication(login, password) != null &&
                    !userService.authentication(login, password).getLogin().equals("admin")) {

                req.getSession().setAttribute("userLogin", login);
                logger.debug("user: " + login + " logged");
                resp.sendRedirect(req.getContextPath() + "/listUser");

            } else if (userService.authentication(login, password) != null &&
                    userService.authentication(login, password).getLogin().equals("admin")) {

                req.getSession().setAttribute("userLogin", login);
                logger.debug("user: " + login + " logged");
                resp.sendRedirect(req.getContextPath() + "/admin");

            } else
                req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

}
