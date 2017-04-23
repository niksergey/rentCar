package main.services;

import main.models.pojo.User;

public interface UserService {
    User auth(String email, String password);
    int register(String email, String phone, String firstName, String secondName,
                     String lastName, String password);
}
