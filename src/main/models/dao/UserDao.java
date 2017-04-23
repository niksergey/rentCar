package main.models.dao;

import main.models.pojo.Leaser;
import main.models.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    User findByPhone(String phone);
    boolean addUser(String email, String phone, String firstName,
                    String secondName, String lastName, String password);
}
