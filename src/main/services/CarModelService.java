package main.services;

import main.models.pojo.CarModel;

import java.util.List;

public interface CarModelService {
    List<CarModel> getAllCarModels();

    CarModel getById(int id);
}
