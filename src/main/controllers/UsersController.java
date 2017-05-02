package main.controllers;

import main.exceptions.DatabaseException;
import main.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/users")
public class UsersController {
    private static final Logger logger = LogManager.getLogger(UsersController.class);

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showUser(Model model) {
        try {
            model.addAttribute("users", userService.getAllUsers());
        } catch (DatabaseException e) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", e.toString());
            return "error";
        }
        return "users/list";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") int id,
                             RedirectAttributes redirectAttributes) {
        String cssStatus;
        String msg;

        logger.debug("deleteUser() id: {}", id);

        try {
            userService.deleteById(id);
            cssStatus = "success";
            msg = "Пользователь удален!";
        } catch (DatabaseException e) {
            cssStatus = "danger";
            msg = "Не удалось удалить пользователя!";
        }

        redirectAttributes.addFlashAttribute("css", cssStatus);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/users";
    }
}
