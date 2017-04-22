package main.services;

import main.models.dao.CarDao;
import main.models.dao.CarDaoImpl;
import main.models.pojo.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CarServiceImpl implements CarService {
    public static final Logger logger = LogManager.getLogger(CarServiceImpl.class.getName());
    private static CarDao carDao = new CarDaoImpl();

    @Override
    public List<Car> getAllCars() {
        return carDao.getAll();
    }
}
