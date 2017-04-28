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

@Controller
@SessionAttributes("userLogin")
public class LoginController  {
    private final static Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm(Model model) {
        String operationStatus = (String) model.asMap().get("operationStatus");
        model.addAttribute("status", operationStatus);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView doLogin(@RequestParam("email") String email,
                                @RequestParam("password") String password) {
        logger.debug("email/Password: " + email + " " + password);

        ModelAndView mav = new ModelAndView();
        String text;
        try {
            User user = userService.auth(email, password);
            if (user == null) {
                text = "Пользователь с такой комбинацией email и пароль не найден";
            } else if (user.isIsActive()) {
                mav.addObject("userEmail", email);
                mav.setViewName("redirect:/cars");
                return mav;
            } else {
                text = "Пользователь заблокирован";
            }
        } catch (DatabaseException e) {
            text = e.toString();
        }

        mav.addObject("operationStatus", text);
        mav.setViewName("redirect:/login");
        return mav;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ModelAndView logout() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/login");
        return mav;
    }

}
