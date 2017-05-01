package main.services;

import main.models.dao.CarDao;
import main.models.pojo.Car;
import main.models.pojo.CarModel;
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

    @Autowired
    private CarModelService carModelService;

    @Override
    public List<Car> getAllCars() {
        List<Car> allCars = carDao.getAll();
        allCars.parallelStream().forEach((car)->car.setCarModel(carModelService.findById(car.getCarModelId())));
        return allCars;
    }

    @Override
    public boolean deleteCarById(int id) {
        carDao.delete(id);
        return false;
    }

    @Override
    public boolean saveOrUpdateCar(Car car) {
        if (findById(car.getId()) == null) {
            return carDao.save(car.getVin(), car.getYear(), car.getCarModelId());
        } else {
            return carDao.update(car.getId(), car.getVin(), car.getYear(),
                    car.getCarModelId());
        }
    }

    @Override
    public Car findById(int id) {
        Car car = carDao.getById(id);
        if (car != null) {
            CarModel cm = carModelService.findById(car.getCarModelId());
            car.setCarModel(cm);
        }
        return car;
    }
}
