package ru.geekbrains.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "*/*")
public class HeaderFilter implements Filter {

    private transient FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        resp.setCharacterEncoding("UTF-8");
        filterConfig.getServletContext().getRequestDispatcher("*/*").include(req, resp);
        chain.doFilter(req, resp);

    }

    @Override
    public void destroy() {
         filterConfig=null;
    }
}

//cd ~/Documents/wildfly-19.1.0.Final/bin
//./standalone.sh
