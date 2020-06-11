package ru.geekbrains;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "ProductServlet", urlPatterns = {"", "/"})
public class ProductServlet extends HttpServlet {

    private static final Logger logger=LoggerFactory.getLogger(ProductServlet.class);

    private List<Product> productList=new ArrayList<>();

    private AtomicLong identity= new AtomicLong(0);

    @Override
    public void init() throws ServletException {

        productList.add(new Product(1L, "Food", "Milk", 50.0));
        productList.add(new Product(2L, "Food", "Bread", 60.0));
        productList.add(new Product(3L, "Food", "Cheese", 850.0));
        productList.add(new Product(4L, "Clothes", "Shirt", 500.0));
        productList.add(new Product(5L, "Clothes", "Jeans", 2500.0));
        productList.add(new Product(6L, "Clothes", "Hat", 1000.0));
        productList.add(new Product(7L, "Dishes", "Plate", 200.0));
        productList.add(new Product(8L, "Dishes", "Cup", 150.0));
        productList.add(new Product(9L, "Dishes", "Spoon", 100.0));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Index product page");
        if(req.getServletPath().equals("/")) {
            req.setAttribute("products", productList);
            getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        }else if (req.getServletPath().equals("/new")) {
            req.setAttribute("prod", new Product());
            getServletContext().getRequestDispatcher("/WEB-INF/views/todo.jsp").forward(req, resp);
        } else if(req.getServletPath().equals("/edit")) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/products.jsp").forward(req, resp);
        } else if(req.getServletPath().equals("/about")) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/about.jsp").forward(req, resp);
        } else if(req.getServletPath().equals("/cart")) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req, resp);
        } else if(req.getServletPath().equals("/order")) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/order.jsp").forward(req, resp);
        } else if(req.getServletPath().equals("/prod_page")) {
            req.setAttribute("products", productList);
            getServletContext().getRequestDispatcher("/WEB-INF/views/prod_page.jsp").forward(req, resp);
        } else resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        productList.add(new Product(identity.incrementAndGet(),
                req.getParameter("category"),
                req.getParameter("title"),
                Double.parseDouble(req.getParameter("price"))));
        resp.sendRedirect(getServletContext().getContextPath());
    }
}
