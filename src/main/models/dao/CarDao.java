package main.models.dao;

import main.models.pojo.Car;

import java.util.List;

public interface CarDao {
    List<Car> getAll();
    Car getById(int id);
    Car getByVin(String vin);
    boolean delete(int id);
    boolean update(int id, String vin, int year, int model);
    boolean save(String vin, int year, int model);
}
