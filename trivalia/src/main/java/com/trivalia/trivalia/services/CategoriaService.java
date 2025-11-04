package com.trivalia.trivalia.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trivalia.trivalia.dto.CategoriaDTO;
import com.trivalia.trivalia.entities.CategoriaEntity;
import com.trivalia.trivalia.entities.PreguntasEntity;
import com.trivalia.trivalia.mappers.CategoriaMapper;
import com.trivalia.trivalia.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final PreguntasService preguntaService;

    public CategoriaService(CategoriaRepository categoriaRepository, PreguntasService preguntaService) {
        this.categoriaRepository = categoriaRepository;
        this.preguntaService = preguntaService;
    }

    public List<CategoriaDTO> obtenerCategorias() {
        List<CategoriaEntity> randomCategoriasList = new ArrayList<>(this.categoriaRepository.findAll());
        Collections.shuffle(randomCategoriasList);
        List<CategoriaDTO> dtoList = randomCategoriasList.stream().map(entity
                -> CategoriaMapper.INSTANCE.toDTO(entity)
        ).toList();
        return dtoList;
    }

    public CategoriaDTO obtenerCategoriaPorId(Long idCategoria) {
        CategoriaEntity categoriaEntity = this.categoriaRepository.findById(idCategoria).orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        CategoriaDTO dto = CategoriaMapper.INSTANCE.toDTO(categoriaEntity);
        return dto;
    }

    public int ObtenerCantidadPreguntas(Long idCategoria) {
        List<PreguntasEntity> preguntasList = this.preguntaService.obtenerListPreguntas(idCategoria);
        return preguntasList.size();
    }

}
