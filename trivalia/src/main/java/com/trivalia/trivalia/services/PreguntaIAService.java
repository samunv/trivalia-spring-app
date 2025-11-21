package com.trivalia.trivalia.services;

import com.trivalia.trivalia.utils.ConversorJsonObjeto;
import com.trivalia.trivalia.utils.IA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.trivalia.trivalia.model.PreguntaDTO;

@Service
public class PreguntaIAService {

    private final Client clienteGemini;
    private final GenerateContentConfig configuracionContenido;
    @Value("${ia.api.key}")
    private String IA_API_KEY;

    public PreguntaIAService(Client clienteGemini, GenerateContentConfig configuracionContenido) {
        this.clienteGemini = clienteGemini;
        this.configuracionContenido = configuracionContenido;
    }

    public PreguntaDTO generarPreguntaIA() {
        IA ia = new IA();
        PreguntaDTO preguntaDTO = ia.obtenerRespuestaIA(clienteGemini, this.obtenerPromptPregunta(), this.configuracionContenido, PreguntaDTO.class);
        return preguntaDTO;
    }

    private String obtenerPromptPregunta() {
        return "Genera una pregunta de trivia considerablemente de dificultad 'MEDIO' (que se traduce en dificultad media), de cultura general, que pueda ser resuelto para la media de personas, pero no puede ser tampoco muy sencilla ni fácil de acertar. \n"
                + "Puede ser de cualquier tema (Por ejemplo equipos de fútbol, música general, ciencia, naturaleza, etc.). Es necesario que el enunciado o pregunta no sea muy larga. Está prohibido que en las opciones se incluyan pistas. Debe estar en formato JSON con las siguientes claves: "
                + "{ \"pregunta\": \"\", \"opcion_a\": \"\", \"opcion_b\": \"\", \"opcion_c\": \"\", "
                + "\"respuesta_correcta\": \"\", \"tipo_pregunta\": \"OPCIONES\", \"dificultad\": \"MEDIO\", \"imagenURL\": \"\" } "
                + "Llena cada campo correctamente. Cada caracter de la respuesta correcta debe coincidir exactamente con los de una de las opciones (opcion_a, opcion_b o opcion_c). No se debe incluir ningun texto más en la respuesta, solamente el JSON exacto. No debe tener saltos de línea. Considera consultar información verídica para generar una pregunta correcta, cuya respuesta correcta sea verdadera y totalmente comprobada";
    }

}
