package main.services;

import main.models.dao.CarModelDao;
import main.models.dao.CarModelDaoImpl;
import main.models.pojo.CarModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CarModelServiceImpl implements CarModelService {
    public static final Logger logger = LogManager.getLogger(CarServiceImpl.class.getName());
    private static CarModelDao carModelDao = new CarModelDaoImpl();

    @Override
    public List<CarModel> getAllCarModels() {
        return carModelDao.getAll();
    }
}
