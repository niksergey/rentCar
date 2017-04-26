package main.controllers;

import main.exceptions.DatabaseException;
import main.services.UserService;
import main.services.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignupServlet extends HttpServlet {
    private final static Logger logger = LogManager.getLogger(SignupServlet.class);

    @Autowired
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        logger.debug("Credentials " + phone +  " " + email);


        int res = -1;
        try {
            res = userService.register(req.getParameter("email"), req.getParameter("phone"),
                    req.getParameter("first_name"), req.getParameter("second_name"),
                    req.getParameter("last_name"), req.getParameter("password"));
        } catch (DatabaseException e) {
            req.setAttribute("servletMsg", e.toString());
        }

        if (res == 0) {
            resp.sendRedirect(req.getContextPath() + "/signin");
        } else {
            resp.sendRedirect(req.getContextPath() + "/signup");
        }

    }
}
