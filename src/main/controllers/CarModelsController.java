package main.controllers;

import main.services.CarModelService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CarModelsController {
    private static final Logger logger = LogManager.getLogger(CarModelsController.class);

    @Autowired
    private CarModelService carModelService;

    @RequestMapping(value = "/carmodels", method = RequestMethod.GET)
    public String showCarModels(Model model) {
        model.addAttribute("carModels", carModelService.getAllCarModels());
        return "carModels/list";
    }
}
