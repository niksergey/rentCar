package main.services;

import main.models.dao.CarModelDao;
import main.models.pojo.CarModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {
    public static final Logger logger = LogManager.getLogger(CarServiceImpl.class.getName());
    @Autowired
    private CarModelDao carModelDao;

    @Override
    public List<CarModel> getAllCarModels() {
        return carModelDao.getAll();
    }

    @Override
    public CarModel findById(int id) {
        return carModelDao.getById(id);
    }

    @Override
    public boolean deleteCarModelById(int id) {
        return carModelDao.delete(id);
    }

    @Override
    public boolean saveOrUpdateCarModel(CarModel carModel) {
        if (carModel.isNew() || findById(carModel.getId()) == null) {
            return carModelDao.save(carModel.getManufacturer(), carModel.getModel(),
                    carModel.getPower(), carModel.getGear());
        } else {
            return carModelDao.update(carModel.getId(), carModel.getManufacturer(),
                    carModel.getModel(), carModel.getPower(), carModel.getGear());
        }
    }
}
