package com.trivalia.trivalia.services.interfaces;

import com.trivalia.trivalia.entities.RefreshTokenEntity;
import com.trivalia.trivalia.model.RefreshTokenDTO;

import java.util.Optional;

public interface RefreshTokenServiceInterface {
    public Optional<RefreshTokenDTO> obtenerRefreshToken(String refreshTokenValor);
    public void guardarRefreshToken(RefreshTokenEntity refreshToken);
    public RefreshTokenDTO crearRefreshToken(String uid);
    public void eliminarRefreshToken(String refreshTokenValor);

}
