package main.controllers;

import main.exceptions.DatabaseException;
import main.models.pojo.User;
import main.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("userLogin")
public class LoginController  {
    private final static Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        String operationStatus = (String) model.asMap().get("operationStatus");
        model.addAttribute("status", operationStatus);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLogin(@RequestParam("email") String email,
                                     @RequestParam("password") String password,
                                     final RedirectAttributes redirectAttributes) {
        logger.debug("email/Password: " + email + " " + password);

        String text;
        try {
            User user = userService.auth(email, password);
            if (user == null) {
                text = "Пользователь с такой комбинацией email и пароль не найден";
            } else if (user.isIsActive()) {
                redirectAttributes.addFlashAttribute("userEmail", email);
                return "redirect:/cars";
            } else {
                text = "Пользователь заблокирован";
            }
        } catch (DatabaseException e) {
            text = e.toString();
        }

        String cssStatus = "danger";
        redirectAttributes.addFlashAttribute("css", cssStatus);
        redirectAttributes.addFlashAttribute("msg", text);
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session,
            final RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Сеанс закончен.");
        return "redirect:/login";
    }

}
