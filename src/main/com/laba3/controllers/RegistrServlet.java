package com.laba3.controllers;

import com.laba3.MyMath;
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

/**
 * Created by root on 28.04.17.
 */
public class RegistrServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(RegistrServlet.class);

    private  UserService userService;

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.
                processInjectionBasedOnServletContext(this,
                        config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Do GET");
        System.out.println(req.getParameter("login"));
        System.out.println(req.getParameter("password"));
        System.out.println(req.getParameter("email"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Do POST");

        String pass = "";
        try {
            pass = MyMath.createMD5(MyMath.createMD5(req.getParameter("password")));

            User user = new User();
            user.setLogin(req.getParameter("login"));
            user.setPassword(pass);
            user.setMail(req.getParameter("email"));
            String action = req.getParameter("action");

            logger.info(user.toString());

            if(action != null && !action.isEmpty()){
                if(action.equals("добавить")){
                    if(userService.addUser(user) == -1) {
                        resp.getWriter().print("error");
                    } else {
                        resp.sendRedirect("/admin");
                    }
                }
            }

            else
            {
                if(userService.addUser(user) == -1) {
                    resp.getWriter().print("error");
                } else {
                    resp.sendRedirect("/");
                }
            }
        } catch (NullPointerException e){
            logger.info(e);
        }

    }
}
