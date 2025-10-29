package com.trivalia.trivalia.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.trivalia.trivalia.config.Jwt;
import com.trivalia.trivalia.dto.JwtClienteDTO;

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

    @PostMapping("/verificar-jwt")
    public Map<String, String> verificarJWTdelCliente(@RequestBody JwtClienteDTO jwtCliente) {
        Map<String, String> respuestaMap = new HashMap<>();
        try {
            if (this.jwt.validarToken(jwtCliente.getJwtCliente())) {
                respuestaMap.put("exito", "Token validado correctamente");
            } else {
                respuestaMap.put("error", "Error al validar el token");
            }

            return respuestaMap;

        } catch (Exception e) {
            // Manejo de error
            respuestaMap.put("error", "Token JWT inválido");
            return respuestaMap;
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
