package main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Такого автомобиля не существует!")
public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(int carId) {
        super("Не найден автомобиль " + carId);
    }
}
