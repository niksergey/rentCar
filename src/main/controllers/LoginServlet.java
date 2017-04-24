package main.controllers;

import main.models.pojo.User;
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
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("delete".equals(req.getParameter("currentSession"))) {
            logger.debug("Close session " + req.getSession().getId());
            req.getSession().invalidate();
            req.setAttribute("servletMsg", "Сессия закрыта");
            resp.sendRedirect(req.getContextPath() + "/signin");
            return;
        }

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        logger.debug("email/Password: " + email + " " + password);

        String text;
        User user = userService.auth(email, password);
        if (user == null ) {
            text = "Пользователь с такой комбинацией email и пароль не найден";
        } else if (user.isIsActive()) {
            req.getSession().setAttribute("userEmail", email);
            resp.sendRedirect(req.getContextPath() + "/car/list");
            return;
        } else {
            text = "Пользователь заблокирован";
        }
        req.setAttribute("servletMsg", text);
        resp.sendRedirect(req.getContextPath() + "/signin");
    }
}
