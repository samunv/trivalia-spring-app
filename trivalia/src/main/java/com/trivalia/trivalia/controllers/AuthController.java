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
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Map<String, String>> login(@RequestBody FirebaseTokenDTO fb, HttpServletResponse response) throws FirebaseAuthException {
        Map<String, Object> tokensMap = this.authServiceInterface.firebaseLogin(fb);
        String accessJWToken = (String) tokensMap.get("accessJWToken");
        RefreshTokenDTO refreshTokenDTO = (RefreshTokenDTO) tokensMap.get("refreshToken");
        Cookie refreshTokenCookie = this.establecerYObtenerRefreshTokenEnHttpCookies(refreshTokenDTO);
        response.addCookie(refreshTokenCookie);
        return ResponseEntity.ok(Map.of("token", accessJWToken));
    }

    @PostMapping("/verificar-jwt")
    public Map<String, String> verificarJWTdelCliente(@RequestBody JwtClienteDTO jwtCliente) {
        return this.authServiceInterface.validarJWT(jwtCliente);
    }

    private Cookie establecerYObtenerRefreshTokenEnHttpCookies(RefreshTokenDTO refreshTokenDTO) {
        Cookie refreshTokenCookie = this.crearCookieConRefreshToken(refreshTokenDTO.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true); // No es accesible desde cliente JS
        //refreshTokenCookie.setSecure(true);   // SOLO se envía en HTTPS
        refreshTokenCookie.setPath("/auth/refresh"); // Solo se envía a endpoints de refresco
        // Configurar el tiempo de vida en segundos
        refreshTokenCookie.setMaxAge((int) (refreshTokenDTO.getExpiracion() / 1000));
        return refreshTokenCookie;
    }

    private Cookie crearCookieConRefreshToken(String refreshTokenValor) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshTokenValor);
        return refreshTokenCookie;
    }


    @PostMapping("/logout/{uid}")
    public ResponseEntity<?> logout(@PathVariable String uid) {
        this.authServiceInterface.logout(uid);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(
                                     @CookieValue(name = "refreshToken", required = false)
                                     String refreshTokenValor,
                                     HttpServletResponse response) {
        String accessJWToken = this.authServiceInterface.refresh(refreshTokenValor);
        return ResponseEntity.ok(Map.of("token", accessJWToken));
    }

}
