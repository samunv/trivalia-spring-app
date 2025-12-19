package com.trivalia.trivalia.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash
public class UsoDeIAEntity {
    @Id
    private String idUsoDeIA;

    @Indexed
    private String uidUsuario;

    @TimeToLive
    private Long renovacion;

    private Integer usosRestantes;

    public UsoDeIAEntity() {}

    public String getIdUsoDeIA() {
        return idUsoDeIA;
    }

    public void setIdUsoDeIA(String idUsoDeIA) {
        this.idUsoDeIA = idUsoDeIA;
    }

    public String getUidUsuario() {
        return uidUsuario;
    }

    public void setUidUsuario(String uidUsuario) {
        this.uidUsuario = uidUsuario;
    }

    public Long getRenovacion() {
        return renovacion;
    }

    public void setRenovacion(Long renovacion) {
        this.renovacion = renovacion;
    }

    public Integer getUsosRestantes() {
        return usosRestantes;
    }

    public void setUsosRestantes(Integer usosRestantes) {
        this.usosRestantes = usosRestantes;
    }
}
