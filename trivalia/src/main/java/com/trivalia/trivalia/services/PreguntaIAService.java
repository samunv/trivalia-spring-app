package com.trivalia.trivalia.services;

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

    private Client clienteGemini;
    @Value("${ia.api.key}")
    private String IA_API_KEY;

    public PreguntaDTO generarPreguntaIA() {
        String PROMPT = this.obtenerPrompt();
        this.clienteGemini = Client.builder().apiKey(IA_API_KEY).build();

        // Obtener configuración del contenido del modelo
        GenerateContentConfig configuracionContenido
                = this.obtenerContentConfig();

        GenerateContentResponse response
                = this.clienteGemini.models.generateContent("gemini-2.5-flash", PROMPT, configuracionContenido);

        String json = response.text();

        PreguntaDTO preguntaDto = this.convertirJSONenPreguntaDTO(json);

        return preguntaDto;
    }

    private PreguntaDTO convertirJSONenPreguntaDTO(String jsonPregunta) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            PreguntaDTO pregunta = mapper.readValue(jsonPregunta, PreguntaDTO.class);
            return pregunta;
        } catch (JsonProcessingException e) {
            System.err.println("Error de JSON Parsing: La IA no devolvió el formato esperado.");
            e.printStackTrace();
            return new PreguntaDTO();
        }

    }

    private GenerateContentConfig obtenerContentConfig() {
        return GenerateContentConfig.builder().temperature(0.8f)
                .responseMimeType("application/json")
                .build();
    }

    private String obtenerPrompt() {
        return "Genera una pregunta (considerablemente difícil para la media de personas) \n"
                + "de trivia de cualquier tema (Por ejemplo equipos de fútbol, música general, ciencia, naturaleza, etc.) en formato JSON con las siguientes claves: "
                + "{ \"pregunta\": \"\", \"opcion_a\": \"\", \"opcion_b\": \"\", \"opcion_c\": \"\", "
                + "\"respuesta_correcta\": \"\", \"tipo_pregunta\": \"OPCIONES\", \"dificultad\": \"DIFICIL\", \"imagenURL\": \"\" } "
                + "Llena cada campo correctamente. Cada caracter de la respuesta correcta debe coincidir exactamente con los de una de las opciones (opcion_a, opcion_b o opcion_c). No se debe incluir ningun texto más en la respuesta, solamente el JSON exacto. No debe tener saltos de línea. Considera consultar información verídica para generar una pregunta correcta, cuya respuesta correcta sea verdadera y totalmente comprobada";
    }

}
