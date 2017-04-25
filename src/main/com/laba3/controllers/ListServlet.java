package main.com.laba3.controllers;

import main.com.laba3.pojo.User;
import main.com.laba3.service.UserService;
import main.com.laba3.service.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by root on 24.04.17.
 */
public class ListServlet extends HttpServlet {

    private static UserService userService = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<User> users = null;
        try {
            users = userService.getAllUser();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        req.setAttribute("users", users);

        getServletContext().getRequestDispatcher("/listUser.jsp")
                .forward(req, resp);
    }

}
