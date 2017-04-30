package com.laba3.controllers;

import com.laba3.MyMath;
import com.laba3.pojo.User;
import com.laba3.service.UserService;
import com.laba3.service.UserServiceImp;
import org.apache.log4j.Logger;

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

    private static UserService userService = new UserServiceImp();

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
