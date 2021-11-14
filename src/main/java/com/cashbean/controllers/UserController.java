package com.cashbean.controllers;

import com.cashbean.dto.LoginRequest;
import com.cashbean.dto.SignupRequest;
import com.cashbean.models.User;
import com.cashbean.services.UserService;
import com.cashbean.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;
    private final CommonUtils commonUtils;

    @Autowired
    public UserController(UserService userService, CommonUtils commonUtils) {
        this.userService = userService;
        this.commonUtils = commonUtils;
    }

    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(HttpServletRequest request) {
        User user = userService.getFromRequest(request);
        if (user != null)
            return ResponseEntity
                    .status(200)
                    .body(user);
        return ResponseEntity.status(401).build();
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequest request, HttpServletResponse response) {
        if (userService.getByEmail(request.getEmail()) == null) {
            User user = userService.addUser(request.getEmail(),
                                            request.getName(),
                                            request.getPassword());
            if (user != null) {
                Cookie cookie = commonUtils.createUserCookie(user);
                response.addCookie(cookie);
                return ResponseEntity.status(200).build();
            }
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(409).build();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest request, HttpServletResponse response) {
        User user = userService.verifyUser(request.getEmail(), request.getPassword());
        if (user != null) {
            Cookie cookie = commonUtils.createUserCookie(user);
            response.addCookie(cookie);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(401).build();
    }
}
