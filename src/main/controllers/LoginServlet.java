package main.controllers;

import main.exceptions.DatabaseException;
import main.models.pojo.User;
import main.services.UserService;
import main.services.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginServlet extends HttpServlet {
    private final static Logger logger = LogManager.getLogger(LoginServlet.class);

    @Autowired
    private  UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

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
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            return;
        }

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        logger.debug("email/Password: " + email + " " + password);

        String text;

        User user;
        try {
            user = userService.auth(email, password);
            if (user == null ) {
                text = "Пользователь с такой комбинацией email и пароль не найден";
            } else if (user.isIsActive()) {
                req.getSession().setAttribute("userEmail", email);
                resp.sendRedirect(req.getContextPath() + "/car/list");
                return;
            } else {
                text = "Пользователь заблокирован";
            }
        } catch (DatabaseException e) {
            text = e.toString();
        }

        req.setAttribute("servletMsg", text);
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }
}
