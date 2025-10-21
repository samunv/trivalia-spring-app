package com.trivalia.trivalia.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.trivalia.trivalia.dto.PreguntaDTO;
import com.trivalia.trivalia.entities.CategoriaEntity;
import com.trivalia.trivalia.entities.PreguntasEntity;
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

    public List<PreguntasEntity> obtenerListPreguntas(Long idCategoria, int limite) {
        List<PreguntasEntity> preguntasList = this.preguntasRepository.findByCategoriaIdCategoria(idCategoria);
        Collections.shuffle(preguntasList);
        if(preguntasList.size()>limite){
            preguntasList = preguntasList.subList(0, limite);
        }
        return preguntasList;

    }

    public List<PreguntasEntity> obtenerListPreguntas(Long idCategoria) {
        List<PreguntasEntity> preguntasList = this.preguntasRepository.findByCategoriaIdCategoria(idCategoria);
        return preguntasList;

    }

    public List<PreguntasEntity> obtenerListPreguntasAleatorias(int limite) {
        List<PreguntasEntity> preguntasList = this.preguntasRepository.findAll();
        Collections.shuffle(preguntasList);
        if(preguntasList.size()>limite){
            preguntasList = preguntasList.subList(0, limite);
        }
        return preguntasList;

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

        CategoriaEntity categoria = this.buscarCategoria(dto.getId_categoria()).orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        pregunta.setCategoria(categoria);
        return preguntasRepository.save(pregunta);
    }

    public Optional<CategoriaEntity> buscarCategoria(Long idCategoria) {
        return this.categoriaRepository.findById(idCategoria);

    }

}
