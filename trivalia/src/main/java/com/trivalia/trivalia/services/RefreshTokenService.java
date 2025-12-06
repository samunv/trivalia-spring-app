package com.trivalia.trivalia.services;

import com.trivalia.trivalia.entities.RefreshTokenEntity;
import com.trivalia.trivalia.model.RefreshTokenDTO;
import com.trivalia.trivalia.repositories.RefreshTokenRepository;
import com.trivalia.trivalia.services.interfaces.RefreshTokenServiceInterface;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService implements RefreshTokenServiceInterface {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${app.jwt.ExpiracionMSrefreshToken}")
    private Long REFRESH_TOKEN_EXPIRACION_MS;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }


    @Override
    public Optional<RefreshTokenDTO> obtenerRefreshToken(String refreshTokenValor) {
        Optional<RefreshTokenEntity> tokenEntity = refreshTokenRepository.findById(refreshTokenValor);

        return tokenEntity.map(entity ->
                new RefreshTokenDTO(entity.getRefreshToken(), entity.getUidUsuario(), entity.getExpiracion())
        );
    }

    @Override
    public void guardarRefreshToken(RefreshTokenEntity refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void eliminarRefreshToken(String uid) {
        // TODO: es necesario cambiar uid por valor del refreshtoken para mayor seguridad
        refreshTokenRepository.deleteByUidUsuario(uid);
    }

    @Override
    public RefreshTokenDTO crearRefreshToken(String uid) {
        // Generar el valor del token
        String tokenValor = UUID.randomUUID().toString();
        // Crear y configurar la entidad para Redis
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        refreshToken.setRefreshToken(tokenValor);
        refreshToken.setUidUsuario(uid);
        refreshToken.setExpiracion(this.REFRESH_TOKEN_EXPIRACION_MS / 1000);
        // Guardar en Redis
        RefreshTokenEntity tokenGuardado = this.refreshTokenRepository.save(refreshToken);
        return new RefreshTokenDTO(tokenGuardado.getRefreshToken(), tokenGuardado.getUidUsuario(), tokenGuardado.getExpiracion());

    }

}
