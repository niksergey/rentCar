package main.models.dao;

import main.models.pojo.Car;

import java.util.List;

public interface CarDao {
    List<Car> getAll();
    Car getById(int id);
}
