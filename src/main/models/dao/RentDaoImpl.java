package main.models.dao;

import main.models.pojo.Rent;
import main.utils.DatabaseManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RentDaoImpl implements  RentDao {
    static final Logger logger = LogManager.getLogger(LeaserDaoImpl.class);

    private CarDao cd;
    private LeaserDao lsd;

    private DatabaseManager databaseManager;

    @Autowired
    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Autowired
    public void setCd(CarDao cd) {
        this.cd = cd;
    }

    @Autowired
    public void setLsd(LeaserDao lsd) {
        this.lsd = lsd;
    }

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
        String query = "SELECT * FROM rent  ORDER BY id ASC;";
        List<Rent> rents = new ArrayList<>(64);

        try (Connection conn = databaseManager.getConnectionFromPool();
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

        try (Connection conn = databaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    rent = createEntity(result);
                }
            }
        } catch (SQLException e ) {
            logger.warn("SQLException during findById()", e);
        }
        return rent;
    }

    @Override
    public int getCurrentRents() throws SQLException {
        String query = "SELECT COUNT(*) FROM rent WHERE started=TRUE AND " +
                "finished=FALSE;";
        try (Connection conn = databaseManager.getConnectionFromPool();
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)) {
            if (result.next()) {
                return result.getInt(1);
            } else {
                throw new SQLException("Impossible result");
            }
        }
    }

    @Override
    public int getFinishedRents() throws SQLException {
        String query = "SELECT COUNT(*) FROM rent WHERE started=TRUE AND " +
                "finished=TRUE;";
        try (Connection conn = databaseManager.getConnectionFromPool();
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(query)) {
            if (result.next()) {
                return result.getInt(1);
            } else {
                throw new SQLException("Impossible result");
            }
        }
    }
}
