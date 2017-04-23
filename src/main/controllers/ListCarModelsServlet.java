package main.controllers;

import main.services.CarModelService;
import main.services.CarModelServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListCarModelsServlet extends HttpServlet {
    private static CarModelService carModelService = new CarModelServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("carModels", carModelService.getAllCarModels());
        getServletContext().getRequestDispatcher("/jsp/carModels.jsp").forward(req, resp);
    }
}
