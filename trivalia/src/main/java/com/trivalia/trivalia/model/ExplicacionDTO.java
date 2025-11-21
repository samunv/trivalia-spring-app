package com.trivalia.trivalia.model;

public class ExplicacionDTO {

    private Long idPregunta;
    private String explicacion;

    public  ExplicacionDTO() {
    }

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }
}
