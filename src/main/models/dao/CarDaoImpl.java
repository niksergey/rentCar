package main.models.dao;

import main.models.pojo.Car;
import main.utils.DatabaseManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {
    static final Logger logger = LogManager.getLogger(CarModelDaoImpl.class.getName());

    static final CarModelDao cmd = new CarModelDaoImpl();

    private Car createEntity(ResultSet result) {
        Car car = null;
        try {
            car = new Car(
                    result.getInt("id"),
                    cmd.getById(result.getInt("model")),
                    result.getString("vin"),
                    result.getInt("year"));
        } catch (SQLException e) {
            logger.warn("Cannot create CarModel from ResultSet", e);
        }
        return car;
    }

    @Override
    public List<Car> getAll() {
        String query = "SELECT * FROM car;";
        List<Car> cars = new ArrayList<>(64);

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             Statement statement = conn.createStatement()) {
            try(ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    cars.add(createEntity(result));
                }
            }
        }  catch (SQLException e) {
            logger.warn("SQLException during getAll()", e);
        }
        return cars;
    }

    @Override
    public Car getById(int id) {
        String query = "SELECT * FROM car WHERE id=?;";
        Car car = null;

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    car = createEntity(result);
                }
            }
        } catch (SQLException e ) {
            logger.warn("SQLException during getById()", e);
        }
        return car;
    }
}
