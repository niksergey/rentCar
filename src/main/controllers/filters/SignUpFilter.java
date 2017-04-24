package main.controllers.filters;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpFilter implements Filter {
    private final static Logger logger = LogManager.getLogger(SignUpFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String userEmail = (String) request.getSession().getAttribute("userEmail");
        String method = request.getMethod();

        if (userEmail == null || "POST".equals(method)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            logger.debug("Already authorized");
            ((HttpServletResponse)servletResponse).sendRedirect(
                    ((HttpServletRequest)servletRequest).getContextPath());
        }
    }

    @Override
    public void destroy() {

    }
}
