package main.exceptions.handlers;

import main.exceptions.CarNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalControllerExceptionHandler
{
    private static final Logger logger = LogManager.getLogger(GlobalControllerExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "no car")
    @ExceptionHandler(value = CarNotFoundException.class)
    public void notFoundError() {
        logger.warn("Запрос к несуществующей странице");
    }

    @ExceptionHandler(value = {SQLException.class, DataAccessException.class})
    public ModelAndView databaseError(Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/errors/databaseError");
        logger.error("Request raised " + exception.getClass().getSimpleName());
        return mav;
    }


}
