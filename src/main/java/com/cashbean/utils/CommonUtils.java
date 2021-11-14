package com.cashbean.utils;

import com.cashbean.models.User;

import javax.servlet.http.Cookie;

public interface CommonUtils {

    Cookie createUserCookie(User user);

}
