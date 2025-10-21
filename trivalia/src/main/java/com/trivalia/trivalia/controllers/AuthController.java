package com.trivalia.trivalia.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.trivalia.trivalia.config.Jwt;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    Jwt jwt;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody FirebaseTokenRequest fb) {
        try {
            // Validar token Firebase
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(fb.getFirebaseToken());

            // Generar tu JWT propio
            String token = jwt.generarToken(decodedToken.getUid());

            Map<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token", token);

            return tokenMap;

        } catch (FirebaseAuthException e) {
            // Manejo de error
            Map<String, String> error = new HashMap<>();
            error.put("error", "Token de FB inválido");
            return error;
        }
    }

    public static class FirebaseTokenRequest {

        private String firebaseToken;

        public FirebaseTokenRequest() {
        }

        public String getFirebaseToken() {
            return firebaseToken;
        }

        public void setFirebaseToken(String firebaseToken) {
            this.firebaseToken = firebaseToken;
        }
    }
}
