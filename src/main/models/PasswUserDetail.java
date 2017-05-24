package main.models;

import main.models.dto.UserDto;
import main.models.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PasswUserDetail implements UserDetails {
    public final Logger logger = LogManager.getLogger(this.getClass());

    private UserDto user;

    public PasswUserDetail(UserDto user) {
        this.user = user;
        logger.debug(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(2);
        for (String role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //todo
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //todo
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //todo
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
