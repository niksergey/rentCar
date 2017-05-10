package main.services;

import main.models.pojo.Car;

import java.sql.SQLException;
import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    int getNumberAllCars();
    int getNumberAvailableCars();
    int getNumberRentedCars();
    Car findById(int id);
    Car findByVin(String vin);
    boolean deleteCarById(int id);
    boolean saveOrUpdateCar(Car car);
}
