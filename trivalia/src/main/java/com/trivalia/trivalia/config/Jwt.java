package com.trivalia.trivalia.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class Jwt {

    @Value("${jwt.firma}")
    private String jwtFirma;
    private final Key key = Keys.hmacShaKeyFor("MiClaveSuperSecretaQueDebeSerLarga1234567890".getBytes());

    public String generarToken(String uid) {
        return Jwts.builder()
                .setSubject(uid)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 259_200_000)) // 3 días
                .signWith(key)
                .compact();
    }

    public String obtenerUid(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
