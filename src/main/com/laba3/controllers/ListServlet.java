package com.laba3.controllers;

import com.laba3.pojo.User;
import com.laba3.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by root on 24.04.17.
 */
@Controller
public class ListServlet extends HttpServlet {

    @Autowired
    private UserService userService;

    final static Logger logger = Logger.getLogger(ListServlet.class);

    //public static UserService getUserService() {
    //    return userService;
    //}


    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        SpringBeanAutowiringSupport.
                processInjectionBasedOnServletContext(this, config.getServletContext());
    }

//это что за пррнография?

    //@Autowired
    //public static void setUserService(UserService userService) {
    //    ListServlet.userService = userService;
    //}

    //    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession httpSession = req.getSession();
//        String add = req.getParameter("add");
//        String delete = req.getParameter("delete");
//        String action = req.getParameter("action");
//
//        if(add.equals(add)){
//
//        }
//
//    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        List<User> users = null;
        try {
            users = userService.getAllUser();
        } catch (ClassNotFoundException e) {
            logger.info(e);
        }

        if (users == null)
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        else {
            req.setAttribute("users", users);

            getServletContext().getRequestDispatcher("/listUser.jsp")
                    .forward(req, resp);
        }
    }

}
