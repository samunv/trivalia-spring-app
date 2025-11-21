package com.trivalia.trivalia.services;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.trivalia.trivalia.model.ExplicacionDTO;
import com.trivalia.trivalia.utils.IA;
import org.springframework.stereotype.Service;

@Service
public class ExplicacionService {

    private final Client clienteGemini;
    private final GenerateContentConfig configuracionContenido;

    public ExplicacionService(Client clienteGemini, GenerateContentConfig configuracionContenido) {
        this.clienteGemini = clienteGemini;
        this.configuracionContenido = configuracionContenido;
    }

    public ExplicacionDTO obtenerExplicacion(String idPregunta) {
        IA ia = new IA();
        ExplicacionDTO explicacionDTO = ia.obtenerRespuestaIA(clienteGemini, this.obtenerPromptExplicacion(), configuracionContenido, ExplicacionDTO.class)
        return explicacionDTO;
    }

    private String obtenerPromptExplicacion(){
        return "";
    }
}
