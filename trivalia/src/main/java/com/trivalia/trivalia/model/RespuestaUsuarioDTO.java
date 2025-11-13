package com.trivalia.trivalia.model;

public class RespuestaUsuarioDTO {

    private Long idPregunta;
    private String respuesta_seleccionada;

    public RespuestaUsuarioDTO() {
    }

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getRespuesta_seleccionada() {
        return respuesta_seleccionada;
    }

    public void setRespuesta_seleccionada(String respuesta_seleccionada) {
        this.respuesta_seleccionada = respuesta_seleccionada;
    }
}
