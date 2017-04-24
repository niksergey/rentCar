package main.controllers;

import main.models.pojo.Car;
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
        String action = req.getParameter("actionType");
        if (action != null) {
            String id = req.getParameter("carId");
            int carId = Integer.valueOf(id);
            switch (action) {
                case "edit":
                    Car car = carService.getCarById(carId);
                    logger.debug("Edit car " + carId + " " + car);
                    req.setAttribute("car", car);
                    getServletContext().getRequestDispatcher("/jsp/carEdit.jsp").forward(req, resp);
                    return;
            }
        }
        req.setAttribute("cars", carService.getAllCars());
        getServletContext().getRequestDispatcher("/jsp/cars.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("actionType");

        if (action != null) {
            String id = req.getParameter("carId");
            int carId = Integer.valueOf(id);
            switch (action) {
                case "saveEdit":
                    carId = Integer.valueOf(req.getParameter("carId"));
                    String vin = req.getParameter("vin");
                    int year = Integer.valueOf(req.getParameter("year"));
                    int model = Integer.valueOf(req.getParameter("model"));
                    carService.editCar(carId, vin, year, model);
                    break;
                case "delete":
                    if (!carService.deleteCarById(carId)) {
                        logger.debug("Coudn't delete car " + carId);
                    }
                    break;
                case "add":
                    break;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/car/list");
    }
}
