package main.models.dao;

import main.models.pojo.User;
import main.utils.DatabaseManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    static final Logger logger = LogManager.getLogger(UserDaoImpl.class.getName());

    private User createEntity(ResultSet result) throws SQLException {
        User user = new User(
                result.getInt("id"),
                result.getString("first_name"),
                result.getString("second_name"),
                result.getString("last_name"),
                result.getString("phone_number"),
                result.getString("email"),
                result.getBoolean("enabled"),
                result.getString("password"));
        return user;
    }

    public List<User> getAll() throws SQLException {
        String query = "SELECT * FROM userentry  ORDER BY id ASC;";
        List<User> users = new ArrayList<>(64);

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             Statement statement = conn.createStatement()) {
            try (ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    users.add(createEntity(result));
                }
            }
        }
        return users;
    }

    public User findByEmailAndPassword(String email, String password)
            throws SQLException {
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
        }

        return user;
    }

    @Override
    public User findByEmail(String email)
            throws SQLException {
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
        }
        return user;
    }

    @Override
    public List<String> findRolesByEmail(String email) throws SQLException {
        String query = "SELECT role FROM user_roles " +
                "WHERE email=?";
        List<String> roles = new ArrayList<>(2);
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    roles.add(result.getString("role"));
                }
            }
        }
        return roles;
    }

    @Override
    public User findByPhone(String phone) throws SQLException {
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
        }

        return user;
    }

    @Override
    public boolean addUser(String email, String phone, String firstName,
                           String secondName, String lastName, String password)
            throws SQLException {
        String query = "INSERT INTO userentry (email, phone_number, first_name," +
                " second_name, last_name, password, enabled) " +
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
        }
        return true;
    }

    @Override
    public User addUser(User user) throws SQLException {
        String query = "INSERT INTO userentry (email, phone_number, first_name," +
                " second_name, last_name, password, enabled) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPhoneNumber());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getSecondName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getPassword());
            statement.setBoolean(7, true);  // TODO
            statement.executeUpdate();
        }

        return user;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        String query = "DELETE FROM userentry WHERE id=?;";
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        return true;
    }
}
