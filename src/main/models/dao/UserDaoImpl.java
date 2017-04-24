package main.models.dao;

import main.models.pojo.Leaser;
import main.models.pojo.User;
import main.utils.DatabaseManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    static final Logger logger = LogManager.getLogger(UserDaoImpl.class.getName());
    private User createEntity(ResultSet result) {
        User user = null;
        try {
            user = new Leaser(
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
            logger.warn("Cannot create User from ResultSet", e);
        }
        return user;
    }

    public List<User> getAll() {
        String query = "SELECT * FROM userentry;";
        List<User> users = new ArrayList<>(64);

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             Statement statement = conn.createStatement()) {
            try(ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    users.add(createEntity(result));
                }
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User findByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM userentry " +
                "WHERE email=? AND password=?";
        User user = null;

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    user = createEntity(result);
                }
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        String query = "SELECT * FROM userentry " +
                "WHERE email=?";
        User user = null;

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    user = createEntity(result);
                }
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByPhone(String phone) {
        String query = "SELECT * FROM userentry " +
                "WHERE phone_number=?";
        User user = null;

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, phone);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    user = createEntity(result);
                }
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean addUser(String email, String phone, String firstName,
                           String secondName, String lastName, String password) {
        String query = "INSERT INTO userentry (email, phone_number, first_name," +
                " second_name, last_name, password, isactive) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, phone);
            statement.setString(3, firstName);
            statement.setString(4, secondName);
            statement.setString(5, lastName);
            statement.setString(6, password);
            statement.setBoolean(7, true);
            statement.executeUpdate();
            return true;
        } catch (SQLException e ) {
            logger.debug("SQLException while inserting user");
        }
        return false;
    }
}
