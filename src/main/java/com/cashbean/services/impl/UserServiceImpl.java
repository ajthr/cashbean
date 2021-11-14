package com.cashbean.services.impl;

import com.cashbean.models.User;
import com.cashbean.repositories.UserRepository;
import com.cashbean.services.UserService;
import com.cashbean.utils.JwtUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public User getById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getFromRequest(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "SUID");
        if (cookie != null) {
            String userId = jwtUtils.verifyToken(cookie.getValue());
            if (userId != null) {
                return this.getById(UUID.fromString(userId));
            }
        }
        return null;
    }

    @Override
    public User addUser(String email, String name, String password) {
        try {
            String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
            User user = new User(email, name, passwordHash);
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User verifyUser(String email, String password) {
        try {
            User user = userRepository.findByEmail(email);
            if (BCrypt.checkpw(password, user.getPassword()))
                return user;
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}
