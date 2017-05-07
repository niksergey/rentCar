package main.services;

import main.models.PasswUserDetail;
import main.models.dao.UserDao;
import main.models.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PasswordEncodedUserDetailService implements UserDetailsService {
    public final Logger logger = LogManager.getLogger(this.getClass());

    private UserDao userRepository;

    public PasswordEncodedUserDetailService() {
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userRepository.findByEmail(email);
            List<String> roles = userRepository.findRolesByEmail(email);
            user.setRoles(roles);
        } catch (SQLException e) {
            logger.warn("User SQL error", e);
        }

        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        return new PasswUserDetail(user);
    }

    @Autowired
    public void setUserService(UserDao userRepository) {
        this.userRepository = userRepository;
    }
}
