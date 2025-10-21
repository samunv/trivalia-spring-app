package com.trivalia.trivalia.dto;

public class CategoriaDTO {

    private Long id_categoria;
    private String titulo;
    private String imagenURL;
    private int cantidad_preguntas;

    public CategoriaDTO() {
    }

    public Long getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Long id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    public int getCantidad_preguntas() {
        return cantidad_preguntas;
    }

    public void setCantidad_preguntas(int cantidad_preguntas) {
        this.cantidad_preguntas = cantidad_preguntas;
    }

    

}
