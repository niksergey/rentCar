package main.models.dao;

import main.models.pojo.CarModel;
import main.utils.DatabaseManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarModelDaoImpl implements CarModelDao {
    static final Logger logger = LogManager.getLogger(CarModelDaoImpl.class.getName());

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
        String query = "SELECT * FROM carmodel;";
        List<CarModel> carModels = new ArrayList<>(64);

        try (Connection conn = DatabaseManager.getConnectionFromPool();
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

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    carModel = createEntity(result);
                }
            }
        } catch (SQLException e ) {
            logger.warn("SQLException during getById()", e);
        }
        return carModel;
    }
}
