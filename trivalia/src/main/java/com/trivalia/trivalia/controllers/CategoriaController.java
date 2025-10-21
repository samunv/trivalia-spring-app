package com.trivalia.trivalia.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trivalia.trivalia.entities.CategoriaEntity;
import com.trivalia.trivalia.services.CategoriaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.trivalia.trivalia.dto.CategoriaDTO;

@RestController
@RequestMapping("api/categorias")

public class CategoriaController {

    private final CategoriaService cs;

    public CategoriaController(CategoriaService cs) {
        this.cs = cs;

    }

    @GetMapping("/todo")
    public List<CategoriaDTO> obtenerCategorias() {
        List<CategoriaEntity> entityList = this.cs.obtenerCategorias();
        System.out.println("Accediendo a /categorias/todo");
        List<CategoriaDTO> dtoList = entityList.stream().map(entity -> {
            CategoriaDTO dto = new CategoriaDTO();
            dto.setId_categoria(entity.getIdCategoria());
            dto.setTitulo(entity.getTitulo());
            dto.setImagenURL(entity.getImagenURL());
            return dto;

        }).toList();

        return dtoList;
    }

    @GetMapping("/obtener/{id_categoria}")
    public CategoriaDTO obtenerCategoriaInfo(@PathVariable Long id_categoria) {
        CategoriaEntity entity = this.cs.obtenerCategoriaPorId(id_categoria);
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId_categoria(entity.getIdCategoria());
        dto.setTitulo(entity.getTitulo());
        dto.setImagenURL(entity.getImagenURL());
        dto.setCantidad_preguntas(this.cs.ObtenerCantidadPreguntas(id_categoria));
        return dto;
    }

}
