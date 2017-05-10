package main.models.dao;

import main.models.pojo.Car;
import main.utils.DatabaseManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {
    static final Logger logger = LogManager.getLogger(CarModelDaoImpl.class.getName());

    private CarModelDao cmd;
    private DatabaseManager databaseManager;

    @Autowired
    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Autowired
    public void setCmd(CarModelDao cmd) {
        this.cmd = cmd;
    }

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
        String query = "SELECT * FROM car ORDER BY id ASC;";
        List<Car> cars = new ArrayList<>(64);

        try (Connection conn = databaseManager.getConnectionFromPool();
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                cars.add(createEntity(result));
            }
        } catch (SQLException ex) {
            throw new UncategorizedSQLException("getAll", query, ex);
        }
        return cars;
    }

    @Override
    public Car getById(int id) {
        String query = "SELECT * FROM car WHERE id=?;";
        Car car = null;

        try (Connection conn = databaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    car = createEntity(result);
                }
            }
        } catch (SQLException ex) {
            throw new UncategorizedSQLException("getById", query, ex);
        }
        return car;
    }

    @Override
    public Car getByVin(String vin) {
        String query = "SELECT * FROM car WHERE vin=?;";
        Car car = null;

        try (Connection conn = databaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, vin);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    car = createEntity(result);
                }
            }
        } catch (SQLException ex) {
            throw new UncategorizedSQLException("getByVin", query, ex);
        }
        return car;
    }

    public boolean save(String vin, int year, int model) {
        String query = "INSERT INTO car (vin, year, model) " +
                " VALUES (?, ?, ?);";
        try (Connection conn = databaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, vin);
            statement.setInt(2, year);
            statement.setInt(3, model);
            statement.executeUpdate();
        }  catch (SQLException ex) {
            throw new UncategorizedSQLException("save", query, ex);
        }
        return true;
    }

    public boolean update(int id, String vin, int year, int model) {
        String query = "UPDATE car SET vin=?, year=?, model=? WHERE id=?;";
        try (Connection conn = databaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, vin);
            statement.setInt(2, year);
            statement.setInt(3, model);
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new UncategorizedSQLException("update", query, ex);
        }
        return true;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM car WHERE id=?;";
        try (Connection conn = databaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new UncategorizedSQLException("delete", query, ex);
        }
        return true;
    }

    @Override
    public int getNumberAllCars() {
        int quantity = 0;

        String query = "SELECT COUNT(*) FROM car;";
        try (Connection conn = databaseManager.getConnectionFromPool();
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)) {
            if (result.next()) {
                quantity = result.getInt(1);
            }
        } catch (SQLException ex) {
            throw new UncategorizedSQLException("getNumberAllCars", query, ex);
        }

        return quantity;
    }

    @Override
    public int getNumberAvailableCars() {
        int quantity = 0;

        String query = "SELECT COUNT(*) FROM car WHERE rented=FALSE;";
        try (Connection conn = databaseManager.getConnectionFromPool();
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)) {
            if (result.next()) {
                quantity = result.getInt(1);
            }
        }  catch (SQLException ex) {
            throw new UncategorizedSQLException("getNumberAvailableCars", query, ex);
        }

        return quantity;
    }

    @Override
    public int getNumberRentedCars() {
        int quantity = 0;

        String query = "SELECT COUNT(*) FROM car WHERE rented=TRUE;";
        try (Connection conn = databaseManager.getConnectionFromPool();
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)) {
            if (result.next()) {
                quantity = result.getInt(1);
            }
        }  catch (SQLException ex) {
            throw new UncategorizedSQLException("getNumberRentedCars", query, ex);
        }

        return quantity;
    }
}
