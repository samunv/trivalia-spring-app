package com.trivalia.trivalia.services.interfaces;
import com.trivalia.trivalia.entities.CategoriaEntity;
import com.trivalia.trivalia.model.CategoriaDTO;

import java.util.List;

public interface CategoriaServiceInterface {
    public List<CategoriaDTO> obtenerCategorias();
    public CategoriaDTO obtenerCategoriaDTOPorId(Long idCategoria);
    public CategoriaEntity obtenerCategoriaEntity(Long idCategoria);
}
