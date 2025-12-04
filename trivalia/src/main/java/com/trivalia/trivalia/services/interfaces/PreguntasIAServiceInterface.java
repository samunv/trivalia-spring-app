package com.trivalia.trivalia.services.interfaces;

import com.trivalia.trivalia.model.PreguntaDTO;
import com.trivalia.trivalia.model.RespuestaUsuarioDTO;

public interface PreguntasIAServiceInterface {
    public PreguntaDTO generarYObtenerPreguntaIA();
    public boolean responderPreguntaIA(String uid, RespuestaUsuarioDTO respuestaUsuarioDTO);
}
