package com.trivalia.trivalia.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.trivalia.trivalia.config.Jwt;
import com.trivalia.trivalia.controllers.AuthController;
import com.trivalia.trivalia.model.FirebaseTokenDTO;
import com.trivalia.trivalia.model.JwtClienteDTO;
import com.trivalia.trivalia.services.interfaces.AuthServiceInterface;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService implements AuthServiceInterface {

    private final Jwt jwt;

    public AuthService(Jwt jwt) {
        this.jwt = jwt;
    }

    @Override
    public Map<String, String> validarJWT(JwtClienteDTO jwtCliente) {
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

    @Override
    public Map<String, String> firebaseLogin(FirebaseTokenDTO fb) {
        try {
            // Validar token Firebase
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(fb.getFirebaseToken());
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
}
