package main.controllers;

import main.services.CarModelService;
import main.services.CarModelServiceImpl;
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

public class ListCarModelsServlet extends HttpServlet {
    private final static Logger logger = LogManager.getLogger(ListCarModelsServlet.class);

    @Autowired
    private CarModelService carModelService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("carModels", carModelService.getAllCarModels());
        getServletContext().getRequestDispatcher("/jsp/carModels.jsp").forward(req, resp);
    }
}
