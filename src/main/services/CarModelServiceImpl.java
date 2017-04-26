package main.services;

import main.models.dao.CarModelDao;
import main.models.dao.CarModelDaoImpl;
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
}
