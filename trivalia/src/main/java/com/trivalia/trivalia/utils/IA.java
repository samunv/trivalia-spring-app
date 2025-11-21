package com.trivalia.trivalia.utils;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;

public class IA {

    public IA (){}

    public <T> T obtenerRespuestaIA(Client clienteGemini, String PROMPT, GenerateContentConfig configuracionContenido, Class<T> claseResultado){
        GenerateContentResponse response
                =  clienteGemini.models.generateContent("gemini-2.5-flash", PROMPT, configuracionContenido);
        String json = response.text();
        T resultadoObjeto = ConversorJsonObjeto.convertirJSONenObjeto(json, claseResultado);
        return resultadoObjeto;
    }
}
