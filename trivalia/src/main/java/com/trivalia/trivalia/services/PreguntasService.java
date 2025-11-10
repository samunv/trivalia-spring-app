package com.trivalia.trivalia.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.trivalia.trivalia.dto.PreguntaDTO;
import com.trivalia.trivalia.entities.CategoriaEntity;
import com.trivalia.trivalia.entities.PreguntasEntity;
import com.trivalia.trivalia.entities.PreguntasEntity.Dificultad;
import com.trivalia.trivalia.mappers.PreguntaMapper;
import com.trivalia.trivalia.repositories.CategoriaRepository;
import com.trivalia.trivalia.repositories.PreguntasRepository;

@Service
public class PreguntasService {

    private final PreguntasRepository preguntasRepository;
    private final CategoriaRepository categoriaRepository;
    private Client clienteGemini;
    //private final WebClient webClient;
    //private final GenerativeModel generativeModel;
    //private final ObjectMapper objectMapper;

    @Value("${ia.api.key}")
    private String IA_API_KEY;

    public PreguntasService(PreguntasRepository preguntasRepository, CategoriaRepository categoriaRepository) {
        this.preguntasRepository = preguntasRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<PreguntaDTO> obtenerListPreguntas(Long idCategoria, int limite) {
        List<PreguntasEntity> preguntasListEntity = this.preguntasRepository.findByCategoriaIdCategoria(idCategoria);
        //Collections.shuffle(preguntasList);
        if (preguntasListEntity.size() > limite) {
            preguntasListEntity = preguntasListEntity.subList(0, limite);
        }
        List<PreguntaDTO> preguntasListDto = preguntasListEntity.stream()
                .map(entity -> {
                    PreguntaDTO dto = PreguntaMapper.INSTANCE.toDTO(entity);
                    dto.setRespuesta_correcta(null); // eliminar respuesta correcta
                    return dto;
                })
                .toList();
        return preguntasListDto;

    }

    public List<PreguntasEntity> obtenerListPreguntas(Long idCategoria) {
        List<PreguntasEntity> preguntasList = this.preguntasRepository.findByCategoriaIdCategoria(idCategoria);
        return preguntasList;

    }

    public List<PreguntaDTO> obtenerListPreguntasAleatorias(int limite) {
        List<PreguntasEntity> preguntasListEntity = this.preguntasRepository.findAll();
        Collections.shuffle(preguntasListEntity);
        if (preguntasListEntity.size() > limite) {
            preguntasListEntity = preguntasListEntity.subList(0, limite);
        }

        List<PreguntaDTO> preguntasListDto = preguntasListEntity.stream()
                .map(entity -> {
                    PreguntaDTO dto = PreguntaMapper.INSTANCE.toDTO(entity);
                    dto.setRespuesta_correcta(null); // eliminar respuesta correcta
                    return dto;
                })
                .toList();
        return preguntasListDto;

    }

    public PreguntasEntity crearPregunta(PreguntaDTO dto) {

        PreguntasEntity pregunta = new PreguntasEntity();
        pregunta.setPregunta(dto.getPregunta());
        pregunta.setRespuesta_correcta(dto.getRespuesta_correcta());
        pregunta.setOpcion_a(dto.getOpcion_a());
        pregunta.setOpcion_b(dto.getOpcion_b());
        pregunta.setOpcion_c(dto.getOpcion_c());
        pregunta.setTipo_pregunta(dto.getTipo_pregunta());
        pregunta.setDificultad(dto.getDificultad());
        pregunta.setImagenURL(dto.getImagenURL());

        if (this.obtenerCantidadPreguntasPorCategoria(dto.getIdCategoria()) <= 15) {
            CategoriaEntity categoria = this.buscarCategoria(dto.getIdCategoria()).orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            pregunta.setCategoria(categoria);
            return preguntasRepository.save(pregunta);
        } else {
            throw new RuntimeException("No se pueden añadir más preguntas a esta categoría");
        }

    }

    public Optional<CategoriaEntity> buscarCategoria(Long idCategoria) {
        return this.categoriaRepository.findById(idCategoria);

    }

    public int obtenerCantidadPreguntasPorCategoria(Long idCategoria) {
        return this.preguntasRepository.findByCategoriaIdCategoria(idCategoria).size();
    }

    public String obtenerRespuestaCorrecta(Long idPregunta) {
        PreguntasEntity entity = this.preguntasRepository.findById(idPregunta).orElseThrow(() -> new RuntimeException("No se ha encontrado"));
        String respuestaCorrecta = entity.getRespuesta_correcta();
        return respuestaCorrecta;
    }

    public PreguntasEntity obtenerPreguntaDificil(Long idPregunta) {
        PreguntasEntity entity = this.preguntasRepository.findById(idPregunta).orElseThrow(() -> new RuntimeException("Error al obtener pregunta"));
        if (entity.getDificultad().equals(Dificultad.DIFICIL)) {
            return entity;
        }
        return null;
    }

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
        return "Genera una pregunta de trivia de cualquier tema (Por ejemplo equipos de fútbol, música general, ciencia, naturaleza, etc.) en formato JSON con las siguientes claves: "
                + "{ \"pregunta\": \"\", \"opcion_a\": \"\", \"opcion_b\": \"\", \"opcion_c\": \"\", "
                + "\"respuesta_correcta\": \"\", \"tipo_pregunta\": \"OPCIONES\", \"dificultad\": \"DIFICIL\", \"imagenURL\": \"\" } "
                + "Llena cada campo correctamente. Cada caracter de La respuesta correcta debe coincidir exactamente con los de una de las opciones (opcion_a, opcion_b o opcion_c). No se debe incluir ningun texto más en la respuesta, solamente el JSON exacto.";
    }

}
