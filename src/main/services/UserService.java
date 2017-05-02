package main.services;

import main.exceptions.DatabaseException;
import main.models.pojo.User;

import java.util.List;

public interface UserService {
    User auth(String email, String password) throws DatabaseException;
    int register(String email, String phone, String firstName, String secondName,
                     String lastName, String password) throws DatabaseException;

    List<User> getAllUsers() throws DatabaseException;
    boolean deleteById(int id) throws DatabaseException;
}
