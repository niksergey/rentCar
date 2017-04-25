package main.services;

import main.exceptions.DatabaseException;
import main.models.dao.UserDao;
import main.models.dao.UserDaoImpl;
import main.models.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {
    static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());
    private  static UserDao userDao = new UserDaoImpl();

    @Override
    public User auth(String email, String password) throws DatabaseException {
        User user = userDao.findByEmailAndPassword(email, password);
        if (user == null) {
            logger.debug("User with these credentials not found");
            return null;
        }

        if (!user.isIsActive()) {
            logger.debug(user + " blocked");
            return user;
        }

        logger.debug("user: " + user.getEmail());

        return user;
    }

    @Override
    public int register(String email, String phone, String firstName,
                        String secondName, String lastName, String password)
            throws DatabaseException {
        int res = 0;
        if (userDao.findByEmail(email) != null)
            res += 1;
        if (userDao.findByPhone(phone) != null)
            res += 2;
        if (res > 0)
            return res;

        if (!userDao.addUser(email, phone, firstName, secondName,
                lastName, password))
            res = 4;

        return res;
    }
}
