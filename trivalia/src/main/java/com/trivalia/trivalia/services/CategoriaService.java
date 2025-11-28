package com.trivalia.trivalia.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.mappers.UsuarioMapper;
import com.trivalia.trivalia.model.UsuarioDTO;
import com.trivalia.trivalia.repositories.PreguntasRepository;
import com.trivalia.trivalia.repositories.UsuarioRepository;
import com.trivalia.trivalia.services.interfaces.CategoriaServiceInterface;
import org.springframework.stereotype.Service;

import com.trivalia.trivalia.entities.CategoriaEntity;
import com.trivalia.trivalia.entities.PreguntasEntity;
import com.trivalia.trivalia.mappers.CategoriaMapper;
import com.trivalia.trivalia.model.CategoriaDTO;
import com.trivalia.trivalia.repositories.CategoriaRepository;

@Service
public class CategoriaService implements CategoriaServiceInterface {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaDTO> obtenerCategorias() {
        List<CategoriaEntity> randomCategoriasList = new ArrayList<>(this.categoriaRepository.findAll());
        Collections.shuffle(randomCategoriasList);
        List<CategoriaDTO> dtoList = randomCategoriasList.stream().map(CategoriaMapper.INSTANCE::toDTO
        ).toList();
        return dtoList;
    }

    public CategoriaDTO obtenerCategoriaDTOPorId(Long idCategoria) {
        CategoriaEntity categoriaEntity = this.categoriaRepository.findById(idCategoria).orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        CategoriaDTO dto = CategoriaMapper.INSTANCE.toDTO(categoriaEntity);
        return dto;
    }

    public CategoriaEntity obtenerCategoriaEntity(Long idCategoria) {
        return this.categoriaRepository.findById(idCategoria).orElse(null);
    }


}
