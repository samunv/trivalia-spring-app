package com.trivalia.trivalia.services.interfaces;

import com.google.genai.types.GenerateContentResponse;
import com.trivalia.trivalia.utils.ConversorJsonObjeto;

public interface InteligenciaArtificialServiceInterface {
    public <T> T obtenerRespuestaIA(String PROMPT, Class<T> claseResultado);
}
