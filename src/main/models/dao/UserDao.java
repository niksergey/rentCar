package main.models.dao;

import main.models.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<User> getAll() throws SQLException;
    User findByEmailAndPassword(String email, String password) throws SQLException;
    User findByEmail(String email) throws SQLException;
    User findByPhone(String phone) throws SQLException;
    boolean addUser(String email, String phone, String firstName,
                    String secondName, String lastName, String password) throws SQLException;
    boolean deleteUser(int id) throws SQLException;
}
