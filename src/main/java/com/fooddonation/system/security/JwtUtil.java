package com.fooddonation.system.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // ==========================================
    // SECRET KEY (MUST BE STRONG IN PROD)
    // ==========================================
    private final String secret =
            "secretKeysecretKeysecretKey123456";

    private final Key key =
            Keys.hmacShaKeyFor(secret.getBytes());

    // Token validity (1 day)
    private static final long JWT_EXPIRATION = 86400000;

    // ==========================================
    // GENERATE TOKEN
    // ==========================================
    public String generateToken(String email, String role) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ==========================================
    // EXTRACT ALL CLAIMS
    // ==========================================
    private Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ==========================================
    // EXTRACT EMAIL
    // ==========================================
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ==========================================
    // EXTRACT ROLE
    // ==========================================
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // ==========================================
    // CHECK EXPIRATION
    // ==========================================
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    // ==========================================
    // VALIDATE TOKEN
    // ==========================================
    public boolean validateToken(String token, String email) {

        try {
            String extractedEmail = extractUsername(token);

            return extractedEmail.equals(email)
                    && !isTokenExpired(token);

        } catch (Exception e) {
            return false; // safer (prevents system crash)
        }
    }
}