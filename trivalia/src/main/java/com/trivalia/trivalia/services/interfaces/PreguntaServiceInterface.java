package com.trivalia.trivalia.services.interfaces;

import com.trivalia.trivalia.entities.PreguntasEntity;
import com.trivalia.trivalia.model.PreguntaDTO;

import java.util.List;

public interface PreguntaServiceInterface {
    PreguntasEntity obtenerPregunta(Long idPregunta);

    List<PreguntasEntity> obtenerListPreguntas(Long idCategoria);

    List<PreguntaDTO> obtenerListPreguntasDTO(Long idCategoria);

    PreguntasEntity crearPregunta(PreguntaDTO dto);

    PreguntasEntity obtenerPreguntaDificil(Long idPregunta);

    PreguntaDTO obtenerSiguientePregunta(Long idCategoria, Long idPreguntaActual);

    boolean esUltimaPreguntaDeCategoria(Long idPregunta);

    Integer obtenerPreguntaIndex(Long idPregunta, Long idCategoria);

    PreguntaDTO obtenerPrimeraPregunta(Long idCategoria);
}
