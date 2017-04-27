package com.laba3.controllers.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by root on 24.04.17.
 */
public class WhiteList implements Filter {

    private String encoding = "utf-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String url = req.getRequestURI();

//        req.getSession().getAttribute("userLogin");

//        if(req.getSession().getAttribute("userLogin") != null && !url.endsWith("login") && !url.endsWith("register") && !url.endsWith("autority")) {
//            System.out.println(req.getSession().getAttribute("userLogin"));
//            resp.sendRedirect("/login");
//            return;
//        }
        if(req.getSession().getAttribute("userLogin") == null && !url.endsWith("login") && !url.endsWith("LogginServlet")){
//            System.out.println("Login is null");
            resp.sendRedirect("/login");
            return;
        }

//        if(req.getSession().getAttribute("userLogin") != null && !url.endsWith("login")){
//            System.out.println("Login is not null");
//        }

        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
