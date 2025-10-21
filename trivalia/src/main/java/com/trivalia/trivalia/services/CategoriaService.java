package com.trivalia.trivalia.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trivalia.trivalia.entities.CategoriaEntity;
import com.trivalia.trivalia.entities.PreguntasEntity;
import com.trivalia.trivalia.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final PreguntasService preguntaService;

    public CategoriaService(CategoriaRepository categoriaRepository, PreguntasService preguntaService) {
        this.categoriaRepository = categoriaRepository;
        this.preguntaService = preguntaService;
    }

    public List<CategoriaEntity> obtenerCategorias() {
        List<CategoriaEntity> randomCategoriasList = new ArrayList<>(this.categoriaRepository.findAll());
        Collections.shuffle(randomCategoriasList);
        return randomCategoriasList;
    }

    public CategoriaEntity obtenerCategoriaPorId(Long idCategoria) {
        CategoriaEntity categoria = this.categoriaRepository.findById(idCategoria).orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        return categoria;
    }

    public int ObtenerCantidadPreguntas(Long idCategoria) {
        List<PreguntasEntity> preguntasList = this.preguntaService.obtenerListPreguntas(idCategoria);
        return preguntasList.size();
    }

}
