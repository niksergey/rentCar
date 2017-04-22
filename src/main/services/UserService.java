package main.services;

import main.models.pojo.User;

public interface UserService {
    User auth(String email, String password);
}
