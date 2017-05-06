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
@RequestMapping("/admin/rents")
public class RentsController  {
    private static final Logger logger = LogManager.getLogger(RentsController.class);

    private RentService rentService;

    @Autowired
    public void setRentService(RentService rentService) {
        this.rentService = rentService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showRents(Model model) {
        model.addAttribute("rents", rentService.getAllRents());
        return "rents/list";
    }
}
