package main.services;

import main.exceptions.EmailExistsException;
import main.models.dto.UserDto;
import main.models.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User auth(String email, String password);
    User registerNewUserAccount(UserDto userDto) throws EmailExistsException;
    List<User> getAllUsers();
    boolean deleteById(int id);
}
