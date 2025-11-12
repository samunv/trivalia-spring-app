package com.trivalia.trivalia.dto;

import java.util.List;

import com.trivalia.trivalia.entities.PreguntasEntity;

public class UsuarioDTO {

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

    public UsuarioDTO() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFotoURL() {
        return fotoURL;
    }

    public void setFotoURL(String fotoURL) {
        this.fotoURL = fotoURL;
    }

    public Integer getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

    public Integer getMonedas() {
        return monedas;
    }

    public void setMonedas(Integer monedas) {
        this.monedas = monedas;
    }

    public Integer getVidas() {
        return vidas;
    }

    public void setVidas(Integer vidas) {
        this.vidas = vidas;
    }

    public Integer getCantidadPreguntasFalladas() {
        return cantidadPreguntasFalladas;
    }

    public void setCantidadPreguntasFalladas(Integer cantidadPreguntasFalladas) {
        this.cantidadPreguntasFalladas = cantidadPreguntasFalladas;
    }

    public Integer getCantidadPartidasGanadas() {
        return cantidadPartidasGanadas;
    }

    public void setCantidadPartidasGanadas(Integer cantidadPartidasGanadas) {
        this.cantidadPartidasGanadas = cantidadPartidasGanadas;
    }

    public Boolean getRegaloDisponible() {
        return regaloDisponible;
    }

    public void setRegaloDisponible(Boolean regaloDisponible) {
        this.regaloDisponible = regaloDisponible;
    }

    public List<Long> getIdsPreguntasGanadas() {
        return idsPreguntasGanadas;
    }

    public void setIdsPreguntasGanadas(List<Long> idsPreguntasGanadas) {
        this.idsPreguntasGanadas = idsPreguntasGanadas;
    }

}
