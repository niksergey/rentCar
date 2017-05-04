package main.exceptions;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(int carId) {
        super("Не найден автомобиль " + carId);
    }
}
