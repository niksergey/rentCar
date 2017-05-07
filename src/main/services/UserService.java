package main.services;

import main.exceptions.EmailExistsException;
import main.models.dto.UserDto;
import main.models.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User auth(String email, String password) throws SQLException;
    User registerNewUserAccount(UserDto userDto) throws EmailExistsException, SQLException;
    List<User> getAllUsers() throws SQLException;
    boolean deleteById(int id) throws SQLException;
}
