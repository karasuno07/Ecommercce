package com.techstore.ecommerce.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.Payload;
import com.techstore.ecommerce.object.entity.cache.RefreshToken;
import com.techstore.ecommerce.object.entity.jpa.User;
import com.techstore.ecommerce.object.mapper.UserMapper;
import com.techstore.ecommerce.object.model.AuthenticationInfo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class JwtService {

    @Value("${com.tech-store.jwt.secret:3618bf61-a667-4139-ac23-4267323e6217}")
    private String jwtSecret;
    @Value("${com.tech-store.jwt.expiration:300000}")
    private long jwtExpiration; // 300000ms = 5 minutes as default

    public String generateAccessToken(@NonNull User user) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", user.getId());
        payload.put("username", user.getPassword());
        payload.put("role", user.getRole().getName());
        payload.put("permissions", user.getRole().getAuthorities());

        JWTCreator.Builder builder = JWT.create();
        builder.withKeyId(UUID.randomUUID().toString());
        builder.withPayload(payload);
        builder.withExpiresAt(new Date(System.currentTimeMillis() + jwtExpiration));

        return builder.sign(Algorithm.HMAC512(jwtSecret));
    }

    public AuthenticationInfo getAuthInfoFromToken(String token) {
        String json = new String(Base64.getDecoder()
                                       .decode(JWT.decode(token).getPayload()));
        Payload payload = new JWTParser().parsePayload(json);
        Map<String, Claim> claims = payload.getClaims();

        AuthenticationInfo authInfo = new AuthenticationInfo();
        authInfo.setId(claims.get("id").asInt());
        authInfo.setUsername(claims.get("username").asString());
        authInfo.setRoleName(claims.get("role").asString());
        authInfo.setPermissions(claims.get("authorities").asList(String.class));

        return authInfo;
    }

    public boolean validateToken(String authToken) {
        try {
            JWT.require(Algorithm.HMAC512(jwtSecret))
               .build()
               .verify(authToken);
            return true;
        } catch (JWTVerificationException | NullPointerException | IllegalArgumentException ex) {
            log.error("VALIDATE TOKEN FAILED: " + ex.getMessage());
            return false;
        }
    }

    public RefreshToken generateRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);

        // TODO: save token to redis database
        return refreshToken;
    }

    public RefreshToken verifyExpiration(String refreshToken) {
        return null;
    }
}
