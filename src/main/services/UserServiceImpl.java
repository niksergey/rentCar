package main.services;

import main.exceptions.EmailExistsException;
import main.models.dao.UserDao;
import main.models.dto.UserDto;
import main.models.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl() {
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User auth(String email, String password) throws SQLException
    {
        User user = userDao.findByEmailAndPassword(email, password) ;
        if (user == null) {
            logger.debug("User with these credentials not found");
            return null;
        }

        if (!user.isEnabled()) {
            logger.debug(user + " blocked");
            return user;
        }

        logger.debug("user: " + user.getEmail());

        return user;
    }

    @Override
    public User registerNewUserAccount(UserDto accountDto) throws EmailExistsException, SQLException {
        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: "
                    + accountDto.getEmail());
        }

        User user = new User(accountDto);
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setRoles(Arrays.asList("ROLE_USER"));
        user.setEnabled(true);
        logger.debug(user);
        return userDao.addUser(user);
    }

    @Override
    public List<User> getAllUsers() throws SQLException
    {
        return userDao.getAll();
    }

    @Override
    public boolean deleteById(int id) throws SQLException
    {
        return userDao.deleteUser(id);
    }

    private boolean emailExist(String email) throws SQLException {
        User user = userDao.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
}
