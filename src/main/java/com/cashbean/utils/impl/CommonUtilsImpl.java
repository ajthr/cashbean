package com.cashbean.utils.impl;

import com.cashbean.models.User;
import com.cashbean.utils.CommonUtils;
import com.cashbean.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class CommonUtilsImpl implements CommonUtils {

    private final JwtUtils jwtUtils;

    @Autowired
    public CommonUtilsImpl(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Cookie createUserCookie(User user) {
        String token = jwtUtils.createToken(user.getUserId());
        Cookie cookie = new Cookie("SUID", token);
        cookie.setHttpOnly(true);
        return cookie;
    }

}
