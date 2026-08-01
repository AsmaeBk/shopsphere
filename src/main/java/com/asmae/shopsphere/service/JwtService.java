package com.asmae.shopsphere.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.asmae.shopsphere.model.User;
import com.asmae.shopsphere.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
    
    @Value("${jwt.secret}")
    private String secret;

    private final UserRepository userRepository;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(User user) {
        return Jwts.builder()
        .subject(user.getUsername())
        .claim("role", user.getRole())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis()+86400000))
        .signWith(getSigningKey()) 
        .compact();
    }

    private Claims extractAllClaims(String token) {

        return Jwts
            .parser()
            .verifyWith(this.getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {

        boolean userExistsInDb = this.extractUsername(token).equals(userDetails.getUsername());

        boolean isTokenNotExpired = this.extractExpiration(token).after(new Date());

        return userExistsInDb && isTokenNotExpired;

    }
}
