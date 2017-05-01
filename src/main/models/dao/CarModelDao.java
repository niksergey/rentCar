package main.models.dao;

import main.models.pojo.CarModel;

import java.util.List;

public interface CarModelDao {
    List<CarModel> getAll();
    CarModel getById(int id);
    boolean save(String manufacturer, String model, int power, String gear);
    boolean update(int id, String manufacturer, String model, int power, String gear);
    boolean delete(int id);
}
