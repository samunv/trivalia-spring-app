package com.trivalia.trivalia.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trivalia.trivalia.entities.CategoriaEntity;
import com.trivalia.trivalia.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaEntity> obtenerCategorias() {
        List<CategoriaEntity> randomCategoriasList = new ArrayList<>(this.categoriaRepository.findAll());
        Collections.shuffle(randomCategoriasList);
        return randomCategoriasList;
    }

}
