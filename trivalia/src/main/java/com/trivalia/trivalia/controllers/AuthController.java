package com.trivalia.trivalia.controllers;

import java.util.HashMap;
import java.util.Map;

import com.trivalia.trivalia.model.FirebaseTokenDTO;
import com.trivalia.trivalia.services.interfaces.AuthServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.trivalia.trivalia.config.Jwt;
import com.trivalia.trivalia.model.JwtClienteDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthServiceInterface authServiceInterface;

    public AuthController(AuthServiceInterface authServiceInterface) {
        this.authServiceInterface = authServiceInterface;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody FirebaseTokenDTO fb) {
        return this.authServiceInterface.firebaseLogin(fb);
    }

    @PostMapping("/verificar-jwt")
    public Map<String, String> verificarJWTdelCliente(@RequestBody JwtClienteDTO jwtCliente) {
        return this.authServiceInterface.validarJWT(jwtCliente);
    }

}
