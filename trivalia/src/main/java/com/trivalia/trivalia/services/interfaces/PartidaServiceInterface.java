package com.trivalia.trivalia.services.interfaces;

import com.trivalia.trivalia.model.PreguntaDTO;
import com.trivalia.trivalia.model.RespuestaUsuarioDTO;
import com.trivalia.trivalia.model.ResultadoPreguntaRespondidaDTO;

public interface PartidaServiceInterface {
    public boolean jugarPartida(String uid);

    public boolean continuarConMonedas(String uid);

    public boolean ganarPartida(String uid, PreguntaDTO preguntaDTO);

    public PreguntaDTO obtenerPrimeraPregunta(Long idCategoria);

    public ResultadoPreguntaRespondidaDTO responderPregunta(String uid, RespuestaUsuarioDTO respuestaUsuario);

    public boolean perderPorTiempo(String uid);

    public void reintentarPartida();
}
