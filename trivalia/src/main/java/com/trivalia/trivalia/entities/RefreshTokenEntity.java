package com.trivalia.trivalia.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("refreshToken")
public class RefreshTokenEntity {
    @Id
    private String refreshToken;

    @Indexed
    private String uidUsuario;

    @TimeToLive
    private Long expiracion;

    public RefreshTokenEntity() {}

    public RefreshTokenEntity(String refreshToken, String uidUsuario, Long expiracion) {
        this.refreshToken = refreshToken;
        this.uidUsuario = uidUsuario;
        this.expiracion = expiracion;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpiracion() {
        return expiracion;
    }

    public void setExpiracion(Long expiracion) {
        this.expiracion = expiracion;
    }

    public String getUidUsuario() {
        return uidUsuario;
    }

    public void setUidUsuario(String uidUsuario) {
        this.uidUsuario = uidUsuario;
    }
}
