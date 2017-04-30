package main.controllers;

import main.exceptions.DatabaseException;
import main.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class usersController {
    private static final Logger logger = LogManager.getLogger(usersController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
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
}
