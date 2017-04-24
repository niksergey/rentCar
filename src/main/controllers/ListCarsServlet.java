package main.controllers;

import main.services.CarService;
import main.services.CarServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListCarsServlet extends HttpServlet {
    private final static Logger logger = LogManager.getLogger(ListCarsServlet.class);

    private static CarService carService = new CarServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars", carService.getAllCars());
        getServletContext().getRequestDispatcher("/jsp/cars.jsp").forward(req, resp);
    }
}