package main.controllers;

import main.models.pojo.Car;
import main.services.CarModelService;
import main.services.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Controller
public class CarsController  {
    private static final Logger logger = LogManager.getLogger(CarsController.class);

    @Autowired
    private CarService carService;

    @Autowired
    private CarModelService carModelService;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String getCars(Model model) {
        logger.info("in /cars GET ");

        List<Car> allCars = carService.getAllCars();
        model.addAttribute("cars", allCars);
        return "/cars/list";
    }

    @RequestMapping(value = "/cars", method = RequestMethod.POST)
    public String saveOrUpdateCar(@ModelAttribute("carForm") @Validated Car car,
                                   BindingResult result, Model model,
                                  final RedirectAttributes redirectAttributes) {
        logger.debug("saveOrUpdateCar() : {}", car);

        if (result.hasErrors()) {
            return "cars/carform";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (car.isNew()) {
                redirectAttributes.addFlashAttribute("msg", "Автомобиль успешно добавлен!");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Автомобиль успешно обновлен!");
            }

            carService.saveOrUpdateCar(car);
            if (car.isNew()) {
                car = carService.findByVin(car.getVin());
            }
            return "redirect:/cars/" + car.getId();
        }
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.GET)
    public String showCar(@PathVariable("id") int id, Model model) {
        logger.debug("showCar() id: {}", id);

        Car car = carService.findById(id);
        if (car == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Автомобиль не найден!");
        }

        model.addAttribute("car", car);

        return "cars/show";
    }

    @RequestMapping(value = "/cars/{id}/delete", method = RequestMethod.POST)
    public String deleteCar(@PathVariable("id") int id,
                            final RedirectAttributes redirectAttributes) {
        String cssStatus;
        String msg;

        logger.debug("delete() id: {}", id);

        if(carService.deleteCarById(id)) {
            cssStatus = "success";
            msg = "Автомобиль удален!";
        } else {
            cssStatus = "danger";
            msg = "Не удалось удалить автомобиль";
        }

        redirectAttributes.addFlashAttribute("css", cssStatus);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/cars";
    }

    // show update form
    @RequestMapping(value = "/cars/{id}/update", method = RequestMethod.GET)
    public String showUpdateCarForm(@PathVariable("id") int id, Model model) {
        logger.debug("showUpdateCarForm() : {}", id);

        Car car = carService.findById(id);
        model.addAttribute("carForm", car);

        model.addAttribute("carModels", carModelService.getAllCarModels());

        return "cars/carform";
    }

    @RequestMapping(value = "/cars/add", method = RequestMethod.GET)
    public String showAddCarForm(Model model) {
        Car car = new Car();
        model.addAttribute("carForm", car);

        model.addAttribute("carModels", carModelService.getAllCarModels());

        return "cars/carform";
    }
}
