package com.trivalia.trivalia.model.builders;

import com.trivalia.trivalia.model.UsuarioDTO;

import java.util.List;

public class UsuarioBuilder {
    private String uid;

    private String nombre;

    private String email;

    private String fotoURL;

    private Integer estrellas;
    private Integer monedas;

    private Integer vidas;

    private Integer cantidadPreguntasFalladas;
    private Integer cantidadPartidasGanadas;

    private Boolean regaloDisponible;

    private List<Long> idsPreguntasGanadas;

    public UsuarioBuilder uid(String uid) {
        this.uid = uid;
        return this;
    }

    public UsuarioBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public UsuarioBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UsuarioBuilder fotoURL(String fotoURL) {
        this.fotoURL = fotoURL;
        return this;
    }

    public UsuarioBuilder estrellas(Integer estrellas) {
        this.estrellas = estrellas;
        return this;
    }

    public UsuarioBuilder monedas(Integer monedas) {
        this.monedas = monedas;
        return this;
    }

    public UsuarioBuilder vidas(Integer vidas) {
        this.vidas = vidas;
        return this;
    }

    public UsuarioBuilder cantidadPreguntasFalladas(Integer cantidadPreguntasFalladas) {
        this.cantidadPreguntasFalladas = cantidadPreguntasFalladas;
        return this;
    }

    public UsuarioBuilder cantidadPartidasGanadas(Integer cantidadPartidasGanadas) {
        this.cantidadPartidasGanadas = cantidadPartidasGanadas;
        return this;
    }

    public UsuarioBuilder regaloDisponible(Boolean regaloDisponible) {
        this.regaloDisponible = regaloDisponible;
        return this;
    }

    public UsuarioBuilder idsPreguntasGanadas(List<Long> idsPreguntasGanadas) {
        this.idsPreguntasGanadas = idsPreguntasGanadas;
        return this;
    }

    public UsuarioDTO build() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUid(this.uid);
        usuario.setNombre(this.nombre);
        usuario.setEmail(this.email);
        usuario.setFotoURL(this.fotoURL);
        usuario.setEstrellas(this.estrellas);
        usuario.setMonedas(this.monedas);
        usuario.setVidas(this.vidas);
        usuario.setCantidadPreguntasFalladas(this.cantidadPreguntasFalladas);
        usuario.setCantidadPartidasGanadas(this.cantidadPartidasGanadas);
        usuario.setRegaloDisponible(this.regaloDisponible);
        usuario.setIdsPreguntasGanadas(this.idsPreguntasGanadas);
        return usuario;
    }
}
