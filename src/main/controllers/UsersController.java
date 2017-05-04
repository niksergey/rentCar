package main.controllers;

import main.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

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
    public String showUser(Model model) throws SQLException {
        model.addAttribute("users", userService.getAllUsers());
        return "users/list";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") int id,
                             RedirectAttributes redirectAttributes)
            throws SQLException
    {
        String cssStatus;
        String msg;

        logger.debug("deleteUser() id: {}", id);

        userService.deleteById(id);
        cssStatus = "success";
        msg = "Пользователь удален!";

        redirectAttributes.addFlashAttribute("css", cssStatus);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/users";
    }
}
