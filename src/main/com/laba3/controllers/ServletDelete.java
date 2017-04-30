package com.laba3.controllers;

import com.laba3.service.UserService;
import com.laba3.service.UserServiceImp;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by root on 27.04.17.
 */
public class ServletDelete extends HttpServlet{

    private static UserService userService = new UserServiceImp();
    final static Logger logger = Logger.getLogger(ServletDelete.class);

//    public static UserService getUserService() {
//        return userService;
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("input");

        try {
            String delete = req.getParameter("delete");
            if (delete != null && !delete.equals("")) {

                int idUser = Integer.parseInt(delete);
//                 UserService userService = new UserServiceImp();
                userService.deleteByIdUser(idUser);

                System.out.println("succes");

                resp.sendRedirect("/admin");

            } else
                resp.getWriter().print("error");
        } catch (Exception e){
            logger.info(e);
//            e.printStackTrace();
        }


        }
    }
