package main.controllers;

import main.services.CarModelService;
import main.services.CarService;
import main.services.RentService;
import main.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
public class WelcomeController {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private CarService carService;
    private CarModelService carModelService;
    private UserService userService;
    private RentService rentService;

    @RequestMapping("/")
    public String mainPage() {
        return "index";
    }

    @RequestMapping("/userpage")
    public String userPage() {
        return "userpage";
    }

    @RequestMapping("/admin")
    public String statisticPage(Model model) throws SQLException {
        model.addAttribute("totalcars", carService.getNumberAllCars());
        model.addAttribute("rentedcars", carService.getNumberRentedCars());
        model.addAttribute("availablecars", carService.getNumberAvailableCars());
        model.addAttribute("currentrents", rentService.getNumberCurrentRents());
        model.addAttribute("finishedrents", rentService.getNumberFinishedRents());

        return "admin/stats";
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Autowired
    public void setCarModelService(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRentService(RentService rentService) {
        this.rentService = rentService;
    }
}
