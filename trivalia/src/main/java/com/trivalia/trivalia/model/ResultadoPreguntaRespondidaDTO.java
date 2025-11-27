package com.trivalia.trivalia.model;

import com.trivalia.trivalia.enums.Item;

public class ResultadoPreguntaRespondidaDTO {

    private boolean esCorrecta;
    private String mensaje;
    private Item itemAfectado;
    private Integer cantidadItemAfectada;
    private boolean continuar;
    private UsuarioDTO usuarioActualizado;
    private PreguntaDTO siguientePregunta;
    private Integer preguntaIndex;

    public ResultadoPreguntaRespondidaDTO() {
    }

    public boolean isEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Item getItemAfectado() {
        return itemAfectado;
    }

    public void setItemAfectado(Item itemAfectado) {
        this.itemAfectado = itemAfectado;
    }

    public Integer getCantidadItemAfectada() {
        return cantidadItemAfectada;
    }

    public void setCantidadItemAfectada(Integer cantidadItemAfectada) {
        this.cantidadItemAfectada = cantidadItemAfectada;
    }

    public boolean isContinuar() {
        return continuar;
    }

    public void setContinuar(boolean continuar) {
        this.continuar = continuar;
    }

    public UsuarioDTO getUsuarioActualizado() {
        return usuarioActualizado;
    }

    public void setUsuarioActualizado(UsuarioDTO usuarioActualizado) {
        this.usuarioActualizado = usuarioActualizado;
    }

    public PreguntaDTO getSiguientePregunta() {
        return siguientePregunta;
    }

    public void setSiguientePregunta(PreguntaDTO siguientePregunta) {
        this.siguientePregunta = siguientePregunta;
    }

    public Integer getPreguntaIndex() {
        return preguntaIndex;
    }

    public void setPreguntaIndex(Integer preguntaIndex) {
        this.preguntaIndex = preguntaIndex;
    }
}


