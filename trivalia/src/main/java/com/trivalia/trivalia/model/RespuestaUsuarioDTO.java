package com.trivalia.trivalia.model;

public class RespuestaUsuarioDTO {

    private Long idPregunta;
    private String respuestaSeleccionada;

    public RespuestaUsuarioDTO() {
    }

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getRespuestaSeleccionada() {
        return respuestaSeleccionada;
    }

    public void setRespuestaseleccionada(String respuestaSeleccionada) {
        this.respuestaSeleccionada = respuestaSeleccionada;
    }
}
