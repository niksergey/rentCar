package main.models.dao;

import main.models.pojo.CarModel;
import main.utils.DatabaseManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarModelDaoImpl implements CarModelDao {
    static final Logger logger = LogManager.getLogger(CarModelDaoImpl.class.getName());

    private DatabaseManager databaseManager;

    @Autowired
    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    private CarModel createEntity(ResultSet result) {
        CarModel carModel = null;
        try {
            carModel = new CarModel(
                    result.getInt("id"),
                    result.getString("manufacturer"),
                    result.getString("model"),
                    result.getString("gear"),
                    result.getInt("power"));
        } catch (SQLException e) {
            logger.warn("Cannot create CarModel from ResultSet", e);
        }
        return carModel;
    }

    @Override
    public List<CarModel> getAll() {
        String query = "SELECT * FROM carmodel ORDER BY id ASC;";
        List<CarModel> carModels = new ArrayList<>(64);

        try (Connection conn = databaseManager.getConnectionFromPool();
             Statement statement = conn.createStatement()) {
            try(ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    carModels.add(createEntity(result));
                }
            }
        }  catch (SQLException e) {
            logger.warn("SQLException during getAll()", e);
        }
        return carModels;
    }

    @Override
    public CarModel getById(int id) {
        String query = "SELECT * FROM carmodel WHERE id=?;";
        CarModel carModel = null;

        try (Connection conn = databaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    carModel = createEntity(result);
                }
            }
        } catch (SQLException e ) {
            logger.warn("SQLException during findById()", e);
        }
        return carModel;
    }

    @Override
    public boolean save(String manufacturer, String model, int power, String gear) {
        String query = "INSERT INTO carmodel (manufacturer, model, power, gear) " +
                " VALUES (?, ?, ?, ?);";
        try (Connection conn = databaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, manufacturer);
            statement.setString(2, model);
            statement.setInt(3, power);
            statement.setString(4, gear);
            statement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            logger.debug("SQLException while inserting carModel", e);
        }
        return false;
    }

    @Override
    public boolean update(int id, String manufacturer, String model, int power, String gear) {
        String query = "UPDATE carmodel SET manufacturer=?, model=?," +
                " power=?, gear=? WHERE id=?;";
        try (Connection conn = databaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, manufacturer);
            statement.setString(2, model);
            statement.setInt(3, power);
            statement.setString(4, gear);
            statement.setInt(5, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            logger.debug("SQLException while updating carModel", e);
        }
        return false;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM carmodel WHERE id=?;";
        try (Connection conn = databaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            logger.debug("SQLException while deleting carModel", e);
        }
        return false;
    }
}
