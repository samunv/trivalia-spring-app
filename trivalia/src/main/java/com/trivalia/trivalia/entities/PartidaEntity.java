package com.trivalia.trivalia.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "partidas")
public class PartidaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id_partida")
    private Long idPartida;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private Integer preguntas_falladas;

    @Column(nullable = false)
    private Integer preguntas_acertadas;

    @Column(nullable = false)
    private Integer estrellas_ganadas;

    @Column(nullable = false)
    private String uid_usuario;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaEntity categoria;

    public PartidaEntity(){}

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getPreguntas_falladas() {
        return preguntas_falladas;
    }

    public void setPreguntas_falladas(Integer preguntas_falladas) {
        this.preguntas_falladas = preguntas_falladas;
    }

    public Integer getPreguntas_acertadas() {
        return preguntas_acertadas;
    }

    public void setPreguntas_acertadas(Integer preguntas_acertadas) {
        this.preguntas_acertadas = preguntas_acertadas;
    }

    public String getUid_usuario() {
        return uid_usuario;
    }

    public void setUid_usuario(String uid_usuario) {
        this.uid_usuario = uid_usuario;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public Integer getEstrellas_ganadas() {
        return estrellas_ganadas;
    }

    public void setEstrellas_ganadas(Integer estrellas_ganadas) {
        this.estrellas_ganadas = estrellas_ganadas;
    }

    

}
