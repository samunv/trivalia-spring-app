package com.trivalia.trivalia.model.builders;

import com.trivalia.trivalia.enums.Item;
import com.trivalia.trivalia.model.PreguntaDTO;
import com.trivalia.trivalia.model.ResultadoPreguntaRespondidaDTO;
import com.trivalia.trivalia.model.UsuarioDTO;

public class ResultadoPreguntaBuilder{
    private boolean esCorrecta;
    private String mensaje;
    private Item itemAfectado;
    private Integer cantidadItemAfectada;
    private boolean continuar;
    private UsuarioDTO usuarioActualizado;
    private PreguntaDTO siguientePregunta;
    private Integer preguntaIndex;

    public ResultadoPreguntaBuilder esCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
        return this;
    }

    public ResultadoPreguntaBuilder mensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    public ResultadoPreguntaBuilder itemAfectado(Item itemAfectado) {
        this.itemAfectado = itemAfectado;
        return this;
    }

    public ResultadoPreguntaBuilder cantidadItemAfectada(Integer cantidadItemAfectada) {
        this.cantidadItemAfectada = cantidadItemAfectada;
        return this;
    }

    public ResultadoPreguntaBuilder continuar(boolean continuar) {
        this.continuar = continuar;
        return this;
    }

    public ResultadoPreguntaBuilder usuarioActualizado(UsuarioDTO usuarioActualizado) {
        this.usuarioActualizado = usuarioActualizado;
        return this;
    }

    public ResultadoPreguntaBuilder siguientePregunta(PreguntaDTO siguientePregunta) {
        this.siguientePregunta = siguientePregunta;
        return this;
    }

    public ResultadoPreguntaBuilder preguntaIndex(Integer preguntaIndex) {
        this.preguntaIndex = preguntaIndex;
        return this;
    }

    public ResultadoPreguntaRespondidaDTO build() {
        ResultadoPreguntaRespondidaDTO dto = new ResultadoPreguntaRespondidaDTO();
        dto.setEsCorrecta(this.esCorrecta);
        dto.setMensaje(this.mensaje);
        dto.setItemAfectado(this.itemAfectado);
        dto.setCantidadItemAfectada(this.cantidadItemAfectada);
        dto.setContinuar(this.continuar);
        dto.setUsuarioActualizado(this.usuarioActualizado);
        dto.setSiguientePregunta(this.siguientePregunta);
        dto.setPreguntaIndex(this.preguntaIndex);
        return dto;
    }
    
}
