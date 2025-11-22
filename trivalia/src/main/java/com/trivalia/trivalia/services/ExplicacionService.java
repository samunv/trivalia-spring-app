package com.trivalia.trivalia.services;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.trivalia.trivalia.model.ExplicacionDTO;
import org.springframework.stereotype.Service;

@Service
public class ExplicacionService {

    private final InteligenciaArtificialService inteligenciaArtificialService;

    public ExplicacionService(InteligenciaArtificialService inteligenciaArtificialService) {
        this.inteligenciaArtificialService = inteligenciaArtificialService;

    }

    public ExplicacionDTO obtenerExplicacion(String idPregunta) {
        ExplicacionDTO explicacionDTO = this.inteligenciaArtificialService.obtenerRespuestaIA(this.obtenerPromptExplicacion(), ExplicacionDTO.class);
        return explicacionDTO;
    }

    private String obtenerPromptExplicacion(){
        return "";
    }
}
