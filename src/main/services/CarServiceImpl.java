package main.services;

import main.models.dao.CarDao;
import main.models.pojo.Car;
import main.models.pojo.CarModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    public static final Logger logger = LogManager.getLogger(CarServiceImpl.class.getName());

    private CarDao carDao;

    private CarModelService carModelService;

    @Autowired
    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }

    @Autowired
    public void setCarModelService(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> allCars = new ArrayList<>();
        for (Car car: carDao.getAll()) {
            CarModel cm = carModelService.findById(car.getCarModelId());
            car.setCarModel(cm);
            allCars.add(car);
        }
        return allCars;
    }

    @Override
    public boolean deleteCarById(int id) {
        return carDao.delete(id);
    }

    @Override
    public boolean saveOrUpdateCar(Car car) {
        Integer carId = car.getId();
        if (carId == null || findById(carId) == null) {
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

    @Override
    public Car findByVin(String vin) {
        Car car = carDao.getByVin(vin);
        if (car != null) {
            CarModel cm = carModelService.findById(car.getCarModelId());
            car.setCarModel(cm);
        }
        return car;
    }

    @Override
    public int getNumberAllCars() {
        return carDao.getNumberAllCars();
    }

    @Override
    public int getNumberAvailableCars() {
        return carDao.getNumberAvailableCars();
    }

    @Override
    public int getNumberRentedCars() {
        return carDao.getNumberRentedCars();
    }
}
