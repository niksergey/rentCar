package main.models.dao;

import main.models.pojo.Leaser;
import main.utils.DatabaseManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LeaserDaoImpl implements LeaserDao {
    static final Logger logger = LogManager.getLogger(LeaserDaoImpl.class.getName());

    private Leaser createEntity(ResultSet result) {
        Leaser leaser = null;
        try {
            leaser = new Leaser(
                    result.getInt("id"),
                    result.getString("first_name"),
                    result.getString("second_name"),
                    result.getString("last_name"),
                    result.getString("phone_number"),
                    result.getString("email"),
                    result.getBoolean("isadmin"),
                    result.getBoolean("isactive"),
                    result.getBoolean("isdeleted"));
        } catch (SQLException e) {
            logger.warn("Cannot create Leaser from ResultSet", e);
        }
        return leaser;
    }

    public List<Leaser> getAll() {
        String query = "SELECT * FROM userentry WHERE isadmin=FALSE";
        List<Leaser> leasers = new ArrayList<>(64);

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             Statement statement = conn.createStatement()) {
            try(ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    leasers.add(createEntity(result));
                }
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return leasers;
    }

    public Leaser getByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM userentry " +
                "WHERE email=? AND password=? AND isactive=TRUE;";
        Leaser leaser = null;

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    leaser = createEntity(result);
                }
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return leaser;
    }

    @Override
    public Leaser getById(int id) {
        String query = "SELECT * FROM userentry WHERE id=?;";
        Leaser leaser = null;

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    leaser = createEntity(result);
                }
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return leaser;
    }
}
