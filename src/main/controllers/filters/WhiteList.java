package main.controllers.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;


public class WhiteList implements Filter {
    private final static Logger logger = LogManager.getLogger(WhiteList.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        String userEmail = (String)session.getAttribute("userEmail");

        logger.debug("Session userEmail" + userEmail);
        if (userEmail != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            logger.debug("Not authorised request");
            ((HttpServletResponse)servletResponse).sendRedirect(
                    ((HttpServletRequest)servletRequest).getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {

    }
}
