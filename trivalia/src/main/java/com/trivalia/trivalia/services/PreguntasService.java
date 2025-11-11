package com.trivalia.trivalia.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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

}
