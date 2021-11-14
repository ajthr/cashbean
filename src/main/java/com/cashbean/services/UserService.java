package com.cashbean.services;

import com.cashbean.models.User;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface UserService {

    User getById(UUID id);
    User getByEmail(String email);
    User getFromRequest(HttpServletRequest request);
    User addUser(String email, String name, String password);
    User verifyUser(String email, String password);

}
