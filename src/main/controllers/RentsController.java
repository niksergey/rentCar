package main.controllers;

import main.services.RentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RentsController  {
    private static final Logger logger = LogManager.getLogger(RentsController.class);

    @Autowired
    private RentService rentService;

    @RequestMapping(value = "/rents", method = RequestMethod.GET)
    public String showRents(Model model) {
        model.addAttribute("rents", rentService.getAllRents());
        return "rents/list";
    }
}
