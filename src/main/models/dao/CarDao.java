package main.models.dao;

import main.models.pojo.Car;

import java.util.List;

public interface CarDao {
    List<Car> getAll();
    Car getById(int id);
    boolean deleteCar(int id);
    boolean editCar(int id, String vin, int year, int model);
    boolean addCar(String vin, int year, int model);
}
