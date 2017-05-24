package main.models.dao;

import main.models.dto.UserDto;
import main.models.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<UserDto> getAll();
    UserDto findByEmail(String email);
    UserDto findByPhone(String phone);
    UserDto addUser(UserDto user);
    boolean deleteUser(int id);
}
