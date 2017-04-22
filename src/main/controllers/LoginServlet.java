package main.controllers;

import main.services.UserService;
import main.services.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginServlet extends HttpServlet {
    private static UserService userService = new UserServiceImpl();
    private final static Logger logger = LogManager.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug(req.getQueryString());
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        logger.debug("email/Password: " + email + "/" + password);
        if (userService.auth(email, password) != null) {
            req.getSession().setAttribute("userLogin", email);
            resp.sendRedirect(req.getContextPath() + "/listCars");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
}
