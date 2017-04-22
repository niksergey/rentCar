package main.models.dao;

import main.models.pojo.CarModel;

import java.util.List;

public interface CarModelDao {
    List<CarModel> getAll();
    CarModel getById(int id);
}
