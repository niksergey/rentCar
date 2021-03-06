package main.controllers;

import main.exceptions.EmailExistsException;
import main.models.dto.UserDto;
import main.models.pojo.User;
import main.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

    @RequestMapping(value = "/logout", method = RequestMethod.POST) //todo
    public String logout(HttpSession session,
            final RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Сеанс закончен.");
        return "redirect:/login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registrationForm";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView registerNewUserAccount(
            @ModelAttribute("user") @Valid UserDto accountDto,
            BindingResult result) throws SQLException
    {
        logger.debug(accountDto);
        User registered;

        if(!result.hasErrors()) {
            registered = createUserAccount(accountDto);
            if (registered == null) {
                result.rejectValue("email", "message.regError",
                        "Пользователь с таким email существует!");
            }
        }

        if(result.hasErrors()) {
            return new ModelAndView("registrationForm", "user", accountDto);
        } else {
            return new ModelAndView("login");
        }
    }

    private User createUserAccount(UserDto accountDto) throws SQLException {
        User registered;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }
}
