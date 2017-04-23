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

public class SignupServlet extends HttpServlet {
    private final static Logger logger = LogManager.getLogger(LoginServlet.class);
    private static UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        logger.debug("Credentials " + phone +  " " + email);
        int res = userService.register(req.getParameter("email"), req.getParameter("phone"),
                req.getParameter("first_name"), req.getParameter("second_name"),
                req.getParameter("last_name"), req.getParameter("password"));

        if (res == 0) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            resp.sendRedirect(req.getContextPath() + "/signup");
        }

    }
}