package com.trivalia.trivalia.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trivalia.trivalia.dto.PreguntaDTO;
import com.trivalia.trivalia.entities.PreguntasEntity;
import com.trivalia.trivalia.services.PreguntasService;

@RestController
@RequestMapping("/api/preguntas")
public class PreguntasController {

    private final PreguntasService ps;

    public PreguntasController(PreguntasService ps) {
        this.ps = ps;
    }

    @GetMapping("obtener/{id_categoria}/{limite}")
    public List<PreguntaDTO> obtenerPreguntasPorCategoria(@PathVariable Long id_categoria, @PathVariable int limite) {
        List<PreguntasEntity> entityList = this.ps.obtenerListPreguntas(id_categoria, limite);

        List<PreguntaDTO> resultado = entityList.stream().map(p -> {
            PreguntaDTO dto = new PreguntaDTO();
            dto.setId_categoria(id_categoria);
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

    @GetMapping("aleatorias/{limite}")
    public List<PreguntaDTO> obtenerPreguntasAleatorias(@PathVariable int limite) {
        List<PreguntasEntity> entityList = this.ps.obtenerListPreguntasAleatorias(limite);

        List<PreguntaDTO> resultado = entityList.stream().map(p -> {
            PreguntaDTO dto = new PreguntaDTO();
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

    @GetMapping("obtener-vista-previa/{id_categoria}")
    public List<PreguntaDTO> obtenerVistaPrevia(@PathVariable Long id_categoria) {
        List<PreguntasEntity> entityList = this.ps.obtenerListPreguntas(id_categoria);

        List<PreguntaDTO> vistaPrevia = entityList.stream().map(e -> {
            PreguntaDTO dto = new PreguntaDTO();
            dto.setPregunta(e.getPregunta());
            dto.setImagenURL(e.getImagenURL());
            dto.setTipo_pregunta(e.getTipo_pregunta());
            dto.setDificultad(e.getDificultad());
            return dto;
        }).toList();

        return vistaPrevia;

    }

    @PostMapping("/crear")
    public Map<String, String> crearPregunta(@RequestBody PreguntaDTO dto) {
        PreguntasEntity nuevaPregunta = this.ps.crearPregunta(dto);
        Map<String, String> mensajeMap = new HashMap<>();
        mensajeMap.put("mensaje", "Pregunta creada: " + nuevaPregunta.toString());
        return mensajeMap;
    }

}
