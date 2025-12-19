package com.trivalia.trivalia.services.interfaces;

import com.trivalia.trivalia.entities.PreguntasEntity;
import com.trivalia.trivalia.model.PreguntaDTO;
import com.trivalia.trivalia.model.RespuestaUsuarioDTO;

import java.util.Map;

public interface PreguntasIAServiceInterface {
    public PreguntaDTO generarYObtenerPreguntaIA(Map<String, PreguntasEntity.Dificultad> dificultadMap);
    public String responderPreguntaIA(String uid, RespuestaUsuarioDTO respuestaUsuarioDTO);
}
