package com.example.porjet_security.services;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtAuthentificationService {
    // Génération d'une clé forte compatible HS256
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String username) {
        long expirationTime = 1000 * 60 * 60; // 1 heure en ms
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)  // Utilisation de la clé sécurisée
                .compact();
    }

    public boolean isValidJwt(String jwt) {
        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt);
            return true; // JWT valide
        } catch (JwtException e) {
            return false; // JWT invalide
        }
    }
}
