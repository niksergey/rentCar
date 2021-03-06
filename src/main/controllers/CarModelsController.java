package main.controllers;

import main.models.pojo.CarModel;
import main.services.CarModelService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/carmodels")
public class CarModelsController {
    private static final Logger logger = LogManager.getLogger(CarModelsController.class);

    private CarModelService carModelService;

    @Autowired
    public void setCarModelService(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showCarModels(Model model) {
        model.addAttribute("carModels", carModelService.getAllCarModels());
        return "carmodels/list";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveOrUpdateCarModel(@ModelAttribute("carModelForm") @Validated CarModel carModel,
                                  BindingResult result, Model model,
                                  final RedirectAttributes redirectAttributes) {
        logger.debug("saveOrUpdateCarModel() : ", carModel);
        if (result.hasErrors()) {
            return "carmodels/carmodelform";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (carModel.isNew()) {
                redirectAttributes.addFlashAttribute("msg", "Модель автомобиля успешно добавлена!");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Модель автомобиля успешно обновлена!");
            }

            carModelService.saveOrUpdateCarModel(carModel);

            if (carModel.isNew()) {
                return "redirect:/admin/carmodels";
            }

            return "redirect:/admin/carmodels/" + carModel.getId();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showCar(@PathVariable("id") int id, Model model) {
        logger.debug("showCar() id: {}", id);

        CarModel carModel = carModelService.findById(id);
        if (carModel == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Автомобиль не найден!");
        }

        model.addAttribute("carModel", carModel);

        return "carmodels/show";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateCarForm(@PathVariable("id") int id, Model model) {

        logger.debug("showUpdateCarForm() : " + id);

        CarModel carModel = carModelService.findById(id);
        if (carModel == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Автомобиль не найден!");
        }

        model.addAttribute("carModelForm", carModel);

        return "carmodels/carmodelform";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String deleteCarModel(@PathVariable("id") int id, Model model,
                                 RedirectAttributes redirectAttributes) {
        logger.debug("deleteCarModel() " + id);
        String cssStatus;
        String msg;

        if (carModelService.deleteCarModelById(id)) {
            cssStatus = "success";
            msg = "Модель автомобиля удалена!";
        } else {
            cssStatus = "danger";
            msg = "Не удалось удалить марку автомобиля";
        }

        redirectAttributes.addFlashAttribute("css", cssStatus);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/admin/carmodels";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddCarModelForm(Model model) {
        logger.debug("showAddCarmodelForm");

        CarModel carModel = new CarModel();
        model.addAttribute("carModelForm", carModel);

        return "carmodels/carmodelform";
    }
}
