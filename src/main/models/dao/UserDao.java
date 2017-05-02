package main.models.dao;

import main.exceptions.DatabaseException;
import main.models.pojo.Leaser;
import main.models.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> getAll() throws DatabaseException;
    User findByEmailAndPassword(String email, String password) throws DatabaseException;
    User findByEmail(String email) throws DatabaseException;
    User findByPhone(String phone) throws DatabaseException;
    boolean addUser(String email, String phone, String firstName,
                    String secondName, String lastName, String password) throws DatabaseException;
    boolean deleteUser(int id) throws DatabaseException;
}
