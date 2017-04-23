package main.controllers;

import main.services.RentService;
import main.services.RentServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListRentsServlet extends HttpServlet {
    private final static Logger logger = LogManager.getLogger(LoginServlet.class);
    private static RentService rentService = new RentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("rents", rentService.getAllRents());
        getServletContext().getRequestDispatcher("/jsp/rents.jsp").forward(req, resp);
    }
}
