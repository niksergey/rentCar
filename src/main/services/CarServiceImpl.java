package main.services;

import main.models.dao.CarDao;
import main.models.pojo.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    public static final Logger logger = LogManager.getLogger(CarServiceImpl.class.getName());

    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> getAllCars() {
        return carDao.getAll();
    }

    @Override
    public boolean deleteCarById(int id) {
        carDao.delete(id);
        return false;
    }

    @Override
    public boolean saveOrUpdateCar(Car car) {
        if (findById(car.getId())==null) {
            return carDao.save(car.getVin(), car.getYear(), car.getCarModel().getId());
        } else {
            return carDao.update(car.getId(), car.getVin(), car.getYear(),
                    car.getCarModel().getId());
        }
    }

    @Override
    public Car findById(int id) {
        return carDao.getById(id);
    }
}
