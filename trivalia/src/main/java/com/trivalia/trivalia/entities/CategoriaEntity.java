package com.trivalia.trivalia.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id_categoria")
    private Long idCategoria;

    @Column(nullable = false, name = "titulo")
    private String titulo;

    @Column(nullable = false, name = "imagenURL")
    private String imagenURL;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PreguntasEntity> preguntas;

    public CategoriaEntity() {
    }

    public CategoriaEntity(String titulo, String imagenURL) {
        this.titulo = titulo;
        this.imagenURL = imagenURL;
    }

    public CategoriaEntity(String titulo, String imagenURL, List<PreguntasEntity> preguntas) {
        this.titulo = titulo;
        this.imagenURL = imagenURL;
        this.preguntas = preguntas;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    public List<PreguntasEntity> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntasEntity> preguntas) {
        this.preguntas = preguntas;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
