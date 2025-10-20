package com.trivalia.trivalia.dto;

public class RequestPreguntasDTO {

    private Long id_categoria;
    private Integer limite = 10;
    private Integer estrellas, monedas, vidas;
    private String dificultad;

    public RequestPreguntasDTO(String dificultad) {
        this.dificultad = dificultad;
    }
    public Integer getLimite() {
        return limite;
    }
    public void setLimite(Integer limite) {
        this.limite = limite;
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
    public String getDificultad() {
        return dificultad;
    }
    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
    public Long getId_categoria() {
        return id_categoria;
    }
    public void setId_categoria(Long id_categoria) {
        this.id_categoria = id_categoria;
    }

   

}
