package ru.geekbrains.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


    @WebServlet(name = "Error403Servlet", urlPatterns = "/error403")
    public class Error403Servlet extends HttpServlet {


        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            if(response.getStatus()==403) {
                response.setContentType("text/html");


                response.getWriter().println("<h1>Error information</h1>");
                response.getWriter().println("Game Over! 403");
            }

        }

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
        }
    }