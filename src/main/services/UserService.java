package main.services;

import main.exceptions.EmailExistsException;
import main.models.dto.UserDto;
import main.models.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    UserDto registerNewUserAccount(UserDto userDto) throws EmailExistsException;
    List<UserDto> getAllUsers();
    boolean deleteById(int id);
}
