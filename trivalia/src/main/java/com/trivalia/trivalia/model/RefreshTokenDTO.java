package com.trivalia.trivalia.model;

public class RefreshTokenDTO {

    private String refreshToken;

    private String uidUsuario;

    private Long expiracion;


    public RefreshTokenDTO() {
    }

    public RefreshTokenDTO(String refreshToken, String uidUsuario, Long expiracion) {
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


    @Override
    public String toString() {
        return "{ refreshTokenValor: "+this.getRefreshToken()+", uid: "+this.getUidUsuario()+", expiracion: "+this.getExpiracion()+"}";
    }
}
