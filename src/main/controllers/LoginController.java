package main.controllers;

import main.models.pojo.User;
import main.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class LoginController  {
    private final static Logger logger = LogManager.getLogger(LoginController.class);

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLogin(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               final RedirectAttributes redirectAttributes,
                               HttpSession session)
            throws SQLException
    {
        logger.debug("email/Password: " + email + " " + password);
        String text;

        User user = userService.auth(email, password);
        if (user == null) {
            text = "Пользователь с такой комбинацией email и пароль не найден";
        } else if (user.isActiveFlag()) {
            session.setAttribute("userEmail", email);
            return "redirect:/cars";
        } else {
            text = "Пользователь заблокирован";
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

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpForm() {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@RequestParam("email") String email,
                         @RequestParam("phone") String phone,
                         @RequestParam("first_name") String first_name,
                         @RequestParam("second_name") String second_name,
                         @RequestParam("last_name") String last_name,
                         @RequestParam("password") String password,
                         RedirectAttributes redirectAttributes)
            throws SQLException
    {
        int res = userService.register(email, phone, first_name, second_name,
                    last_name, password);

        if (res == 0) {
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("msg", "Не удалось зарегистрировать пользователя");
            return "redirect:/signup";
        }
    }
}
