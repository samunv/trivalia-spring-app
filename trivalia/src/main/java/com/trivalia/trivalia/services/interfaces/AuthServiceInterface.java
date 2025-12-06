package com.trivalia.trivalia.services.interfaces;

import com.trivalia.trivalia.model.FirebaseTokenDTO;
import com.trivalia.trivalia.model.JwtClienteDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface AuthServiceInterface {
    public Map<String, String> validarJWT(JwtClienteDTO jwt);
    public Map<String, Object>  firebaseLogin(FirebaseTokenDTO fb);
    public void logout(String uid);
    public String refresh(String refreshTokenValor);
}
