package com.cashbean.utils;

import java.util.UUID;

public interface JwtUtils {

    String createToken(UUID userId);
    String verifyToken(String token);

}
