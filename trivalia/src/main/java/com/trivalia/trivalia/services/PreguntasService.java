package com.trivalia.trivalia.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.trivalia.trivalia.mappers.UsuarioMapper;
import com.trivalia.trivalia.model.*;
import org.springframework.stereotype.Service;

import com.trivalia.trivalia.entities.CategoriaEntity;
import com.trivalia.trivalia.entities.PreguntasEntity;
import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.entities.PreguntasEntity.Dificultad;
import com.trivalia.trivalia.enums.Item;
import com.trivalia.trivalia.enums.Operaciones;
import com.trivalia.trivalia.mappers.PreguntaMapper;
import com.trivalia.trivalia.repositories.CategoriaRepository;
import com.trivalia.trivalia.repositories.PreguntasRepository;
import com.trivalia.trivalia.repositories.UsuarioRepository;

@Service
public class PreguntasService {

    private final PreguntasRepository preguntasRepository;
    private final CategoriaService categoriaService;

    public PreguntasService(PreguntasRepository preguntasRepository, CategoriaService categoriaService) {
        this.preguntasRepository = preguntasRepository;
        this.categoriaService = categoriaService;
    }

    public PreguntasEntity obtenerPregunta(Long idPregunta) {
        return this.preguntasRepository.findById(idPregunta).get();
    }

    public List<PreguntaDTO> obtenerListPreguntas(Long idCategoria, int limite) {
        List<PreguntasEntity> preguntasListEntity = this.preguntasRepository.findByCategoriaIdCategoria(idCategoria);
        //Collections.shuffle(preguntasList);
        if (preguntasListEntity.size() > limite) {
            preguntasListEntity = preguntasListEntity.subList(0, limite);
        }
        List<PreguntaDTO> preguntasListDto = preguntasListEntity.stream().map(entity -> {
            PreguntaDTO dto = PreguntaMapper.INSTANCE.toDTO(entity);
            dto.setRespuesta_correcta(null); // eliminar respuesta correcta
            return dto;
        }).toList();
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

        List<PreguntaDTO> preguntasListDto = preguntasListEntity.stream().map(entity -> {
            PreguntaDTO dto = PreguntaMapper.INSTANCE.toDTO(entity);
            dto.setRespuesta_correcta(null); // eliminar respuesta correcta
            return dto;
        }).toList();
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
            CategoriaEntity categoria = this.obtenerCategoria(dto.getIdCategoria());
            pregunta.setCategoria(categoria);
            return preguntasRepository.save(pregunta);
        } else {
            throw new RuntimeException("No se pueden añadir más preguntas a esta categoría");
        }
    }

    public CategoriaEntity obtenerCategoria(Long idCategoria) {
        return this.categoriaService.obtenerCategoriaEntity(idCategoria);
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


    public boolean verificarIndexPregunta(Long idPregunta) {
        Long idCategoria = this.obtenerPregunta(idPregunta).getCategoria().getIdCategoria();
        List<PreguntasEntity> preguntasEntityList = this.obtenerListPreguntas(idCategoria);
        Long ultimoIdPregunta = preguntasEntityList.get(preguntasEntityList.size()-1).getIdPregunta();
        return idPregunta.equals(ultimoIdPregunta);
    }


}
