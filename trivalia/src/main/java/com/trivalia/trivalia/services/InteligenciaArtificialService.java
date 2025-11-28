package com.trivalia.trivalia.services;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.trivalia.trivalia.services.interfaces.InteligenciaArtificialServiceInterface;
import com.trivalia.trivalia.utils.ConversorJsonObjeto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InteligenciaArtificialService implements InteligenciaArtificialServiceInterface {

    private final Client clienteGemini;
    private final GenerateContentConfig configuracionContenido;

    public InteligenciaArtificialService(Client clienteGemini, GenerateContentConfig configuracionContenido) {
        this.clienteGemini = clienteGemini;
        this.configuracionContenido = configuracionContenido;
    }

    public <T> T obtenerRespuestaIA(String PROMPT, Class<T> claseResultado){
        GenerateContentResponse response
                =  this.clienteGemini.models.generateContent("gemini-2.5-flash", PROMPT, this.configuracionContenido);
        String json = response.text();
        T resultadoObjeto = ConversorJsonObjeto.convertirJSONenObjeto(json, claseResultado);
        return resultadoObjeto;
    }
}
