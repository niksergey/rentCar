package main.controllers;

import main.exceptions.CarNotFoundException;
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

import java.util.List;


@Controller
@RequestMapping("/admin/cars")
public class CarsController  {
    private static final Logger logger = LogManager.getLogger(CarsController.class);

    private CarService carService;
    private CarModelService carModelService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Autowired
    public void setCarModelService(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getCars(Model model) {
        logger.info("in /cars GET ");

        List<Car> allCars = carService.getAllCars();
        model.addAttribute("cars", allCars);
        return "cars/list";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveOrUpdateCar(@ModelAttribute("carForm") @Validated Car car,
                                   BindingResult result,
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showCar(@PathVariable("id") int id, Model model) throws CarNotFoundException {
        logger.debug("showCar() id: {}", id);

        Car car = carService.findById(id);
        if (car == null) {
            throw new CarNotFoundException(id);
        }

        model.addAttribute("car", car);
        return "cars/show";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
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

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateCarForm(@PathVariable("id") int id, Model model) {
        logger.debug("showUpdateCarForm() : {}", id);

        Car car = carService.findById(id);
        model.addAttribute("carForm", car);

        model.addAttribute("carModels", carModelService.getAllCarModels());

        return "cars/carform";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddCarForm(Model model) {
        Car car = new Car();
        model.addAttribute("carForm", car);

        model.addAttribute("carModels", carModelService.getAllCarModels());

        return "cars/carform";
    }
}
