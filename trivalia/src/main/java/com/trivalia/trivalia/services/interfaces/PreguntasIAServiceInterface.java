package com.trivalia.trivalia.services.interfaces;

import com.trivalia.trivalia.model.PreguntaDTO;

public interface PreguntasIAServiceInterface {
    public PreguntaDTO generarPreguntaIA();
    public boolean ganarPreguntaIA(String uid);
}
