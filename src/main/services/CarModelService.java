package main.services;

import main.models.pojo.CarModel;

import java.util.List;

public interface CarModelService {
    List<CarModel> getAllCarModels();

    CarModel findById(int id);

    boolean deleteCarModelById(int id);
    boolean saveOrUpdateCarModel(CarModel carModel);
}
