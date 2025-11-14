package com.trivalia.trivalia.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trivalia.trivalia.entities.PreguntasEntity;
import com.trivalia.trivalia.mappers.PreguntaMapper;
import com.trivalia.trivalia.model.PreguntaDTO;
import com.trivalia.trivalia.model.RespuestaUsuarioDTO;
import com.trivalia.trivalia.model.ResultadoPreguntaRespondidaDTO;
import com.trivalia.trivalia.model.UsuarioDTO;
import com.trivalia.trivalia.services.PreguntaIAService;
import com.trivalia.trivalia.services.PreguntasService;

@RestController
@RequestMapping("/api/preguntas")
public class PreguntasController {

    private final PreguntasService preguntaService;
    private final PreguntaIAService preguntaIAService;

    public PreguntasController(PreguntasService preguntaService, PreguntaIAService preguntaIAService) {
        this.preguntaService = preguntaService;
        this.preguntaIAService = preguntaIAService;
    }

    @GetMapping("/obtener/{id_categoria}/{limite}")
    public List<PreguntaDTO> obtenerPreguntasPorCategoria(@PathVariable Long id_categoria, @PathVariable int limite) {
        return this.preguntaService.obtenerListPreguntas(id_categoria, limite);
    }

    @GetMapping("/aleatorias/{limite}")
    public List<PreguntaDTO> obtenerPreguntasAleatorias(@PathVariable int limite) {
        return this.preguntaService.obtenerListPreguntasAleatorias(limite);
    }

    @PostMapping("/crear")
    public Map<String, String> crearPregunta(@RequestBody PreguntaDTO dto) {
        PreguntasEntity nuevaPregunta = this.preguntaService.crearPregunta(dto);
        Map<String, String> mensajeMap = new HashMap<>();
        mensajeMap.put("mensaje", "Pregunta creada: " + nuevaPregunta.toString());
        return mensajeMap;
    }

    @PostMapping("/obtener-dificiles")
    public List<PreguntaDTO> obtenerDificiles(@RequestBody Long[] arrayIdPreguntas) {
        List<PreguntasEntity> entityList = new ArrayList<>();
        for (Long idPregunta : arrayIdPreguntas) {
            entityList.add(this.preguntaService.obtenerPreguntaDificil(idPregunta));
        }

        List<PreguntaDTO> dtoList = entityList.stream().map((entity) -> {
            PreguntaDTO dto = PreguntaMapper.INSTANCE.toDTO(entity);
            if (dto != null) {
                dto.setRespuesta_correcta(null);
            }

            return dto;
        }).filter(dto -> dto != null).toList();

        return dtoList;
    }

    @GetMapping("/obtener-respuesta-correcta/{idPregunta}")
    public Map<String, String> obtenerRespuestaCorrecta(@PathVariable Long idPregunta) {
        Map<String, String> respuestaMap = new HashMap<>();
        respuestaMap.put("respuesta_correcta", this.preguntaService.obtenerRespuestaCorrecta(idPregunta));
        return respuestaMap;
    }

    @GetMapping("/obtener-pregunta-ia")
    public PreguntaDTO obtenerPreguntaIA() {
        return this.preguntaIAService.generarPreguntaIA();
    }

    @PreAuthorize("#uid == authentication.name")
    @PostMapping("/responder/{uid}")
    public ResultadoPreguntaRespondidaDTO responderPregunta(@PathVariable String uid, @RequestBody RespuestaUsuarioDTO respuestaUsuario) {
      return this.preguntaService.responderPregunta(uid, respuestaUsuario);
    }

}
