package main.services;

import main.models.pojo.Car;

import java.sql.SQLException;
import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    int getNumberAllCars() throws SQLException;
    int getNumberAvailableCars() throws SQLException;
    int getNumberRentedCars() throws SQLException;
    Car findById(int id);
    Car findByVin(String vin);
    boolean deleteCarById(int id);
    boolean saveOrUpdateCar(Car car);
}
