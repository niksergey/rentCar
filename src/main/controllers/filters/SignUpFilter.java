package main.controllers.filters;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
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
        String userEmail = (String)((HttpServletRequest) servletRequest)
                .getSession().getAttribute("userEmail");
        if (userEmail == null) {
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
