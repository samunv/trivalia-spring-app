package com.trivalia.trivalia.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @Column(nullable = false, unique = true, name = "uid")
    private String uid;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String fotoURL;

    @Column(nullable = false)
    private Integer estrellas;

    @Column(nullable = false)
    private Integer monedas;

    @Column(nullable = false)
    private Integer vidas;

    @Column(nullable = false)
    private Integer cantidadPreguntasFalladas;

    @Column(nullable = false)
    private Integer cantidadPartidasGanadas;

    @Column(nullable = false)
    private Boolean regaloDisponible;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "usuario_pregunta_ganada", // Nombre de la tabla de unión
            joinColumns = @JoinColumn(name = "uid"), // Columna para el ID del usuario
            inverseJoinColumns = @JoinColumn(name = "id_pregunta") // Columna para el ID de la pregunta
    )
    private List<PreguntasEntity> preguntasGanadas;

    public UsuarioEntity() {
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

    public List<PreguntasEntity> getPreguntasGanadas() {
        return preguntasGanadas;
    }

    public void setPreguntasGanadas(List<PreguntasEntity> preguntasGanadas) {
        this.preguntasGanadas = preguntasGanadas;
    }

}