package main.controllers;

import main.services.CarService;
import main.services.CarServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CarsServlet extends HttpServlet {
    private static CarService carService = new CarServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars", carService.getAllCars());
        getServletContext().getRequestDispatcher("/cars.jsp").forward(req, resp);
    }
}
