package main.services;

import main.models.pojo.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    Car getCarById(int id);
    boolean deleteCarById(int id);
    boolean editCar(int id, String vin, int year, int model);
    boolean addCar(String vin, int year, int model);
}
