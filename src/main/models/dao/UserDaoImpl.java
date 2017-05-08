package main.models.dao;

import main.models.pojo.User;
import main.utils.DatabaseManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
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

    public List<User> getAll() {
        String query = "SELECT * FROM userentry  ORDER BY id ASC;";
        List<User> users = new ArrayList<>(64);

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             Statement statement = conn.createStatement()) {
            try (ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    users.add(createEntity(result));
                }
            }
        } catch (SQLException ex) {
            throw new UncategorizedSQLException("getAll", query, ex);
        }

        return users;
    }

    public User findByEmailAndPassword(String email, String password)
    {
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
        }  catch (SQLException ex) {
            throw new UncategorizedSQLException("findByEmailAndPassword", query, ex);
        }

        return user;
    }

    @Override
    public User findByEmail(String email)
    {
        String query = "SELECT * FROM userentry " +
                "WHERE email=?";

        User user = null;

        logger.warn("User email " + email);
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    user = createEntity(result);
                    logger.warn(user);
                }
            }
        } catch (SQLException ex) {
            throw new UncategorizedSQLException("findByEmail", query, ex);
        }

        return user;
    }

    @Override
    public List<String> findRolesByEmail(String email) {
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
        } catch (SQLException ex) {
            throw new UncategorizedSQLException("findRolesByEmail", query, ex);
        }

        return roles;
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
        } catch (SQLException ex) {
            throw new UncategorizedSQLException("findByPhone", query, ex);
        }

        return user;
    }

    @Override
    public boolean addUser(String email, String phone, String firstName,
                           String secondName, String lastName, String password)
    {
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
        } catch (SQLException ex) {
            throw new UncategorizedSQLException("addUser", query, ex);
        }
        return true;
    }

    @Override
    public User addUser(User user) {
        String query = "INSERT INTO userentry (email, phone_number, first_name," +
                " second_name, last_name, password, enabled) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?);";
        String roleQuery = "INSERT INTO user_roles (email, role) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query);
             PreparedStatement roleStatement = conn.prepareStatement(roleQuery)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPhoneNumber());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getSecondName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getPassword());
            statement.setBoolean(7, true);  // TODO
            statement.executeUpdate();

            for (String role : user.getRoles()) {
                roleStatement.setString(1, user.getEmail());
                roleStatement.setString(2, role);
                roleStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new UncategorizedSQLException("addUser", query, ex);
        }

        return user;
    }

    @Override
    public boolean deleteUser(int id) {
        String query = "DELETE FROM userentry WHERE id=?;";
        try (Connection conn = DatabaseManager.getConnectionFromPool();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new UncategorizedSQLException("deleteUser", query, ex);
        }

        return true;
    }
}
