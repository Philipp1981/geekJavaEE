package ru.geekbrains.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;



@WebServlet(name = "FirstHttpServlet", urlPatterns = "/first_http_servlet/*")
public class FirstHTTPServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        req.setAttribute("headerText", "FirstHTTPServlet");
        resp.getWriter().println("Hello!");
        resp.getWriter().println("<p1>contextPath: " + req.getContextPath() + "</p1>");
        resp.getWriter().println("<p1>servletPath: " + req.getServletPath() + "</p1>");
        resp.getWriter().println("<p1>pathInfo: " + req.getPathInfo() + "</p1>");
        resp.getWriter().println("<p1>queryString: " + req.getQueryString() + "</p1>");

        resp.setHeader("Our-Header", "value");

        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements()){
            String name=names.nextElement();
            resp.getWriter().println("<p1>" + name + ":" + req.getParameter(name) +"</p1>");
        }
  //      resp.sendRedirect("https://ya.ru");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/header");
    }
}
