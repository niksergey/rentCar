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
    public UserDto registerNewUserAccount(UserDto accountDto) throws EmailExistsException {

        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: "
                    + accountDto.getEmail());
        }

        accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        accountDto.setRoles(Arrays.asList("ROLE_USER"));
        accountDto.setEnabled(true);
        logger.debug(accountDto);
        return userDao.addUser(accountDto);
    }

    @Override
    public List<UserDto> getAllUsers()
    {
        return userDao.getAll();
    }

    @Override
    public boolean deleteById(int id)
    {
        return userDao.deleteUser(id);
    }

    private boolean emailExist(String email) {
        UserDto user = userDao.findByEmail(email);
        return user != null;
    }
}
