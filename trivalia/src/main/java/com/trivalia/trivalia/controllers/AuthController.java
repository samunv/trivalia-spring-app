package com.trivalia.trivalia.controllers;

import java.util.HashMap;
import java.util.Map;

import com.trivalia.trivalia.model.FirebaseTokenDTO;
import com.trivalia.trivalia.model.PreguntaDTO;
import com.trivalia.trivalia.model.RefreshTokenDTO;
import com.trivalia.trivalia.services.interfaces.AuthServiceInterface;
import com.trivalia.trivalia.services.interfaces.RefreshTokenServiceInterface;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.trivalia.trivalia.config.Jwt;
import com.trivalia.trivalia.model.JwtClienteDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthServiceInterface authServiceInterface;
    private final RefreshTokenServiceInterface refreshTokenServiceInterface;

    public AuthController(AuthServiceInterface authServiceInterface, RefreshTokenServiceInterface refreshTokenServiceInterface) {
        this.authServiceInterface = authServiceInterface;
        this.refreshTokenServiceInterface = refreshTokenServiceInterface;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody FirebaseTokenDTO fb, HttpServletResponse response) throws FirebaseAuthException {
        Map<String, Object> tokensMap = this.authServiceInterface.firebaseLogin(fb);
        String accessJWToken = (String) tokensMap.get("accessJWToken");
        RefreshTokenDTO refreshTokenDTO = (RefreshTokenDTO) tokensMap.get("refreshToken");

        System.out.println("accessJWToken: " + accessJWToken);
        System.out.println("refreshToken: " + refreshTokenDTO.getRefreshToken());

        return this.obtenerRespuesta(response, accessJWToken, refreshTokenDTO);
    }


    @PostMapping("/logout/{uid}")
    public ResponseEntity<?> logout(@PathVariable String uid) {
        this.authServiceInterface.logout(uid);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, Object>> refresh(
            @CookieValue(name = "refreshToken", required = false)
            String refreshTokenValor,
            HttpServletResponse response) {

        System.out.println("Accediendo a /auth/refresh >>> refreshTokenValor: " + refreshTokenValor);
        System.out.println("Obteniendo Cookie de refreshToken: " + refreshTokenValor);
        if (refreshTokenValor == null || refreshTokenValor.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    Map.of("resultado", false)
            );
        }


        Map<String, Object> tokensCreados = this.authServiceInterface.refrescarTokens(refreshTokenValor);
        String accessJWToken = (String) tokensCreados.get("accessJWToken");
        RefreshTokenDTO refreshTokenDTO = (RefreshTokenDTO) tokensCreados.get("refreshToken");

        System.out.println("Nuevo refreshToken: " + refreshTokenDTO.getRefreshToken());

        return this.obtenerRespuesta(response, accessJWToken, refreshTokenDTO);
    }

    @NotNull
    private ResponseEntity<Map<String, Object>> obtenerRespuesta(HttpServletResponse response, String accessJWToken, RefreshTokenDTO refreshTokenDTO) {
        ResponseCookie cookiesRefrehToken = this.obtenerCookiesConTokens("refreshToken", refreshTokenDTO.getRefreshToken(), refreshTokenDTO.getExpiracion());
        ResponseCookie cookiesAccessJWToken = this.obtenerCookiesConTokens("accessJWToken", accessJWToken, 1_800_000L / 1000);

        response.addHeader(HttpHeaders.SET_COOKIE, cookiesRefrehToken.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, cookiesAccessJWToken.toString());

        return ResponseEntity.ok().body(Map.of("resultado", true));
    }

    private ResponseCookie obtenerCookiesConTokens(String claveToken, String token, Long expiracion) {
        return ResponseCookie.from(claveToken, token)
                .httpOnly(true)
                .secure(false) // True en producción
                .path("/")
                .maxAge(expiracion)
                .sameSite("Lax")
                .build();
    }

}
