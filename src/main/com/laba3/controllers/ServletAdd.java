package com.laba3.controllers;

import com.laba3.pojo.User;
import com.laba3.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by root on 27.04.17.
 */
public class ServletAdd extends HttpServlet{

    private static UserService userService;

    public static UserService getUserService() {
        return userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        long id = Integer.valueOf(req.getParameter("id"));
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String mail = req.getParameter("mail");
        int role_id = Integer.valueOf(req.getParameter("role_id"));
        User user = new User(id, login, password, mail, role_id);

        long is_adding = userService.addUser(user);
        req.setAttribute("is_adding", is_adding);
        resp.sendRedirect(req.getContextPath() + "/add");

        }
    }
