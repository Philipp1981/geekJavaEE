package ru.geekbrains.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartServlet", urlPatterns = "/cart/*")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println("<h1>Cart</h1>");

        resp.getWriter().println("<br>");
        resp.getWriter().printf("<a href=main/>Main</a>");
        resp.getWriter().println("<br>");
        resp.getWriter().printf("<a href=catalog/>Catalog</a>");
        resp.getWriter().println("<br>");
        resp.getWriter().printf("<a href=product/>Product</a>");
        resp.getWriter().println("<br>");
  //      resp.getWriter().printf("<a href=cart/*>Cart</a>");
        resp.getWriter().printf("<a href=order/>Order</a>");
    }
}
