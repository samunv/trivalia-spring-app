package com.trivalia.trivalia.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trivalia.trivalia.dto.PreguntaDTO;
import com.trivalia.trivalia.dto.RequestPreguntasDTO;
import com.trivalia.trivalia.entities.PreguntasEntity;
import com.trivalia.trivalia.services.PreguntasService;

@RestController
@RequestMapping("/api/preguntas")
public class PreguntasController {

    private final PreguntasService ps;

    public PreguntasController(PreguntasService ps) {
        this.ps = ps;
    }

    @PostMapping("/obtener-preguntas")
    public List<PreguntaDTO> obtenerPreguntasPorIdCategoria(@RequestBody RequestPreguntasDTO body) {
        List<PreguntasEntity> entityList = this.ps.obtenerListPreguntas(body.getId_categoria(), body.getLimite());

        System.out.println("BODY ID CATEGORIA>>> " + body.getId_categoria());

        List<PreguntaDTO> resultado = entityList.stream().map(p -> {
            PreguntaDTO dto = new PreguntaDTO();
            dto.setId_categoria(body.getId_categoria());
            dto.setId_pregunta(p.getIdPregunta());
            dto.setPregunta(p.getPregunta());
            dto.setOpcion_a(p.getOpcion_a());
            dto.setOpcion_b(p.getOpcion_b());
            dto.setOpcion_c(p.getOpcion_c());
            dto.setRespuesta_correcta(p.getRespuesta_correcta());
            dto.setTipo_pregunta(p.getTipo_pregunta());
            dto.setDificultad(p.getDificultad());
            dto.setImagenURL(p.getImagenURL());
            return dto;
        }).toList();

        return resultado;
    }

    @PostMapping("/crear")
    public Map<String, String> crearPregunta(@RequestBody PreguntaDTO dto) {
        PreguntasEntity nuevaPregunta = this.ps.crearPregunta(dto);
        Map<String, String> mensajeMap = new HashMap<>();
        mensajeMap.put("mensaje", "Pregunta creada: " + nuevaPregunta);
        return mensajeMap;
    }

}
