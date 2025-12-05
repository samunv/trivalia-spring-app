package com.trivalia.trivalia.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class Jwt {
    private final Key key;

    // Inyecta la firma JWT al crear el bean.
    public Jwt(@Value("${jwt.firma}") String jwtFirma) {
        if (jwtFirma == null || jwtFirma.isEmpty()) {
            throw new IllegalArgumentException("La propiedad 'jwt.firma' no puede ser nula o vacía.");
        }
        // contiene el valor inyectado por Spring.
        this.key = Keys.hmacShaKeyFor(jwtFirma.getBytes());
    }

    public String generarToken(String uid) {
        return Jwts.builder()
                .setSubject(uid)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1_800_000)) // 30 min
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
            throw e;
        }
    }
}
