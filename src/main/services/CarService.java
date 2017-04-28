package main.services;

import main.models.pojo.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    Car findById(int id);
    boolean deleteCarById(int id);
    boolean saveOrUpdateCar(Car car);
}
