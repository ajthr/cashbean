package com.cashbean.utils.impl;

import com.cashbean.utils.JwtUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JwtUtilsImpl implements JwtUtils {

    @Value("${spring.application.secret_key}")
    private String secretKey;

    @Override
    public String createToken(UUID userId) {
        try {
            return Jwts.builder()
                    .setSubject(String.valueOf(userId))
                    .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)))
                    .compact();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String verifyToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
