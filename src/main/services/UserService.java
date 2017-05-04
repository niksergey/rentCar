package main.services;

import main.models.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User auth(String email, String password) throws SQLException;
    int register(String email, String phone, String firstName, String secondName,
                     String lastName, String password) throws SQLException;

    List<User> getAllUsers() throws SQLException;
    boolean deleteById(int id) throws SQLException;
}
