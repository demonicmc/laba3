package com.laba3.controllers;

import com.laba3.pojo.User;
import com.laba3.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by root on 27.04.17.
 */
public class AdminServlet extends HttpServlet {

    private UserService userService;
    final static Logger logger = Logger.getLogger(AdminServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.
                processInjectionBasedOnServletContext(this,
                        config.getServletContext());
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/login.jsp").forward(req, resp);
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        List<User> users = null;
        try {
            users = userService.getAllUser();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (users == null)
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        else {
            req.setAttribute("users", users);

            getServletContext().getRequestDispatcher("/admin.jsp")
                    .forward(req, resp);
        }
    }

}
