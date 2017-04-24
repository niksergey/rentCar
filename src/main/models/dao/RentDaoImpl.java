package main.models.dao;

import main.models.pojo.Rent;
import main.utils.DatabaseManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentDaoImpl implements  RentDao {
    static final Logger logger = LogManager.getLogger(LeaserDaoImpl.class.getName());

    private static CarDao cd = new CarDaoImpl();
    private static LeaserDao lsd = new LeaserDaoImpl();

    private Rent createEntity(ResultSet result) {
        Rent car = null;
        try {
            car = new Rent(
                    result.getInt("id"),
                    result.getTimestamp("start_time"),
                    result.getTimestamp("finish_time"),
                    lsd.getById(result.getInt("leaser")),
                    cd.getById(result.getInt("car")));
        } catch (SQLException e) {
            logger.warn("Cannot create Rent from ResultSet", e);
        }
        return car;
    }

    @Override
    public List<Rent> getAll() {
        String query = "SELECT * FROM rent;";
        List<Rent> rents = new ArrayList<>(64);

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             Statement statement = conn.createStatement()) {
            try(ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    rents.add(createEntity(result));
                }
            }
        }  catch (SQLException e) {
            logger.warn("SQLException during getAll()", e);
        }
        return rents;
    }

    @Override
    public Rent getById(int id) {
        String query = "SELECT * FROM rent WHERE id=?;";
        Rent rent = null;

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    rent = createEntity(result);
                }
            }
        } catch (SQLException e ) {
            logger.warn("SQLException during getById()", e);
        }
        return rent;
    }
}