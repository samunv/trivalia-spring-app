package com.trivalia.trivalia.services;

import java.util.List;

import com.trivalia.trivalia.model.*;
import com.trivalia.trivalia.services.interfaces.CategoriaServiceInterface;
import com.trivalia.trivalia.services.interfaces.ContadorServiceInterface;
import com.trivalia.trivalia.services.interfaces.PreguntaServiceInterface;
import org.springframework.stereotype.Service;

import com.trivalia.trivalia.entities.CategoriaEntity;
import com.trivalia.trivalia.entities.PreguntasEntity;
import com.trivalia.trivalia.entities.PreguntasEntity.Dificultad;
import com.trivalia.trivalia.mappers.PreguntaMapper;
import com.trivalia.trivalia.repositories.PreguntasRepository;

@Service
public class PreguntasService implements PreguntaServiceInterface {

    private final PreguntasRepository preguntasRepository;
    private final CategoriaServiceInterface categoriaService;


    public PreguntasService(PreguntasRepository preguntasRepository, CategoriaServiceInterface categoriaService) {
        this.preguntasRepository = preguntasRepository;
        this.categoriaService = categoriaService;
    }

    public PreguntasEntity obtenerPregunta(Long idPregunta) {
        return this.preguntasRepository.findById(idPregunta).get();
    }


    public List<PreguntasEntity> obtenerListPreguntas(Long idCategoria) {
        List<PreguntasEntity> preguntasList = this.preguntasRepository.findByCategoriaIdCategoria(idCategoria);
        return preguntasList;

    }

    @Override
    public List<PreguntaDTO> obtenerListPreguntasDTO(Long idCategoria) {
        return this.obtenerListPreguntas(idCategoria).stream().map(PreguntaMapper.INSTANCE::toDTO).toList();
    }

    public PreguntasEntity crearPregunta(PreguntaDTO dto) {

        PreguntasEntity pregunta = PreguntaMapper.INSTANCE.toEntity(dto);

        if (this.obtenerCantidadPreguntasPorCategoria(dto.getIdCategoria()) <= 15) {
            CategoriaEntity categoria = this.obtenerCategoria(dto.getIdCategoria());
            pregunta.setCategoria(categoria);
            return preguntasRepository.save(pregunta);
        } else {
            throw new RuntimeException("No se pueden añadir más preguntas a esta categoría");
        }
    }

    private CategoriaEntity obtenerCategoria(Long idCategoria) {
        return this.categoriaService.obtenerCategoriaEntity(idCategoria);
    }

    private int obtenerCantidadPreguntasPorCategoria(Long idCategoria) {
        return this.preguntasRepository.findByCategoriaIdCategoria(idCategoria).size();
    }


    public PreguntasEntity obtenerPreguntaDificil(Long idPregunta) {
        PreguntasEntity entity = this.preguntasRepository.findById(idPregunta).orElseThrow(() -> new RuntimeException("Error al obtener pregunta"));
        if (entity.getDificultad().equals(Dificultad.DIFICIL)) {
            return entity;
        }
        return null;
    }


    public boolean esUltimaPreguntaDeCategoria(Long idPregunta) {
        Long idCategoria = this.obtenerPregunta(idPregunta).getCategoria().getIdCategoria();
        List<PreguntasEntity> preguntasEntityList = this.obtenerListPreguntas(idCategoria);
        Long ultimoIdPregunta = preguntasEntityList.get(preguntasEntityList.size() - 1).getIdPregunta();
        return idPregunta.equals(ultimoIdPregunta);
    }

    public PreguntaDTO obtenerSiguientePregunta(Long idCategoria, Long idPreguntaActual) {
        List<PreguntasEntity> listaPreguntas = this.obtenerListPreguntas(idCategoria);
        List<Long> listaIdsPreguntas = listaPreguntas.stream().map(PreguntasEntity::getIdPregunta).toList();
        int indiceActual = listaIdsPreguntas.indexOf(idPreguntaActual);
        // El índice del elemento siguiente es:
        int indiceSiguiente = indiceActual + 1;
        // Verificación de Límite
        if (indiceSiguiente < listaPreguntas.size()) {

            PreguntasEntity preguntaSiguiente = listaPreguntas.get(indiceSiguiente);
            return PreguntaMapper.INSTANCE.toDTO(preguntaSiguiente);
        } else {
            System.out.println("El índice actual " + indiceActual + ", es el último, no hay un elemento siguiente.");
            return null;
        }
    }

    public Integer obtenerPreguntaIndex(Long idPregunta, Long idCategoria) {
        List<PreguntasEntity> preguntas = this.obtenerListPreguntas(idCategoria);
        return preguntas.indexOf(this.obtenerPregunta(idPregunta)) + 1;
    }


    public PreguntaDTO obtenerPrimeraPregunta(Long idCategoria) {
        List<PreguntasEntity> listaPreguntas = this.obtenerListPreguntas(idCategoria);
        return PreguntaMapper.INSTANCE.toDTO(listaPreguntas.get(0));
    }


}
