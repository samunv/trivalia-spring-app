package com.trivalia.trivalia.model;

import com.trivalia.trivalia.enums.Item;

public class ResultadoPreguntaRespondidaDTO {

    private boolean esCorrecta;
    private String mensaje;
    private Item itemAfectado;
    private Integer cantidadItemAfectada;
    private boolean continuar;

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

}
