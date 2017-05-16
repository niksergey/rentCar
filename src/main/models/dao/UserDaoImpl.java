package main.models.dao;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import main.models.entities.UserRolesEntity;
import main.models.entities.UserentryEntity;
import main.models.pojo.User;
import main.utils.DatabaseManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    static final Logger logger = LogManager.getLogger(UserDaoImpl.class.getName());

    private DatabaseManager databaseManager;
    private SessionFactory sessionFactory;
    private MapperFacade mapper;

    public UserDaoImpl() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(UserentryEntity.class, User.class)
                .field("userRoles{role}", "roles{}")
                .byDefault()
                .register();
        mapper = mapperFactory.getMapperFacade();
    }

    @Autowired
    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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

        try (Connection conn = databaseManager.getConnectionFromPool();
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

        try (Connection conn = databaseManager.getConnectionFromPool();
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
        User user = null;

        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(
                "from UserentryEntity where email = :email");
        query.setParameter("email", email);

        for (Object obj : query.list()) {
            user = mapper.map((UserentryEntity)obj, User.class);
        }

        currentSession.close();

        return user;
    }

//    @Override
    public List<String> findRolesByEmail(String email) {
        Session currentSession = sessionFactory.openSession();
        UserRolesEntity userRolesEntity = currentSession.get(UserRolesEntity.class,
                6);
        List<String> roles = new ArrayList<>();
        roles.add(userRolesEntity.getRole());
        return roles;
    }

    @Override
    public User findByPhone(String phone) {
        String query = "SELECT * FROM userentry " +
                "WHERE phone_number=?";
        User user = null;

        try (Connection conn = databaseManager.getConnectionFromPool();
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
        try (Connection conn = databaseManager.getConnectionFromPool();
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

        try (Connection conn = databaseManager.getConnectionFromPool();
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
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("delete UserentryEntity where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        session.close();
        return result > 0;
    }
}
