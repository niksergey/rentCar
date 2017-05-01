package main.models.dao;

import main.models.pojo.Car;
import main.utils.DatabaseManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {
    static final Logger logger = LogManager.getLogger(CarModelDaoImpl.class.getName());

    @Autowired
    private CarModelDao cmd;

    private Car createEntity(ResultSet result) {
        Car car = null;
        try {
            car = new Car(
                    result.getInt("id"),
                    result.getInt("model"),
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

    public boolean save(String vin, int year, int model) {
        String query = "INSERT INTO car (vin, year, model) " +
                " VALUES (?, ?, ?);";
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, vin);
            statement.setInt(2, year);
            statement.setInt(3, model);
            statement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            logger.debug("SQLException while inserting car");
        }
        return false;
    }

    public boolean update(int id, String vin, int year, int model) {
        String query = "UPDATE car SET vin=?, year=?, model=? WHERE id=?;";
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, vin);
            statement.setInt(2, year);
            statement.setInt(3, model);
            statement.setInt(4, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM car WHERE id=?;";
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return false;
    }
}
