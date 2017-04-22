package main.services;

import main.models.dao.UserDao;
import main.models.dao.UserDaoImpl;
import main.models.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {
    static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());
    private  static UserDao userDao = new UserDaoImpl();

    @Override
    public User auth(String email, String password) {
        User user = userDao.findByEmailAndPassword(email, password);
        if (user == null) {
            logger.debug("User with these credentials not found");
            return null;
        }

        if (!user.isIsActive()) {
            logger.debug(user + " blocked");
            return null;
        }

        logger.debug("user: " + user.getEmail());

        return user;
    }
}
