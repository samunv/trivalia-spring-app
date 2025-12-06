package com.trivalia.trivalia.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.trivalia.trivalia.config.Jwt;
import com.trivalia.trivalia.controllers.AuthController;
import com.trivalia.trivalia.entities.RefreshTokenEntity;
import com.trivalia.trivalia.model.FirebaseTokenDTO;
import com.trivalia.trivalia.model.JwtClienteDTO;
import com.trivalia.trivalia.model.RefreshTokenDTO;
import com.trivalia.trivalia.services.interfaces.AuthServiceInterface;
import com.trivalia.trivalia.services.interfaces.RefreshTokenServiceInterface;
import com.trivalia.trivalia.services.interfaces.UsuarioLecturaServiceInterface;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AuthService implements AuthServiceInterface {

    private final Jwt jwt;
    private final UsuarioLecturaServiceInterface usuarioLecturaService;
    private final RefreshTokenServiceInterface refreshTokenService;

    public AuthService(Jwt jwt, UsuarioLecturaServiceInterface usuarioLecturaService, RefreshTokenServiceInterface refreshTokenService) {
        this.jwt = jwt;
        this.usuarioLecturaService = usuarioLecturaService;
        this.refreshTokenService = refreshTokenService;
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
    public Map<String, Object> firebaseLogin(FirebaseTokenDTO fb) {
        try {
            // Validar token Firebase
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(fb.getFirebaseToken());
            String token = jwt.generarToken(decodedToken.getUid());
            Map<String, Object> tokensMap = new HashMap<>();
            tokensMap.put("accessJWToken", token);
            tokensMap.put("refreshToken", this.crearYObtenerRefreshToken(decodedToken.getUid()));
            return tokensMap;

        } catch (FirebaseAuthException e) {
            // Manejo de error
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Token de FB inválido");
            return error;
        }
    }

    @Override
    public void logout(String uid) {
        System.out.print("Eliminando refresh token por Logout.");
        this.refreshTokenService.eliminarRefreshToken(uid);
    }

    @Override
    public String refresh(String refreshTokenValor) {
        System.out.print("Validando refresh token; valor del refreshToken: " + refreshTokenValor);
        RefreshTokenDTO refreshTokenDTO = this.refreshTokenService.obtenerRefreshToken(refreshTokenValor).orElse(null);
        if (refreshTokenDTO == null) {
            throw new RuntimeException("Refresh token inexistente");
        }
        String accessJWToken = this.jwt.generarToken(refreshTokenDTO.getUidUsuario());
        System.out.println("Generando nuevo JWT >>> " + refreshTokenDTO.getRefreshToken());
        return accessJWToken;
    }


    private RefreshTokenDTO crearYObtenerRefreshToken(String uid) {
        RefreshTokenDTO dto = this.refreshTokenService.crearRefreshToken(uid);
        System.out.println("Creando refresh token: " + dto.toString());
        RefreshTokenDTO refreshTokenDTO =this.refreshTokenService.obtenerRefreshToken(dto.getRefreshToken()).orElse(null);
        System.out.println("Obteniendo refresh token: " + refreshTokenDTO.toString());
        return refreshTokenDTO;
    }
}
