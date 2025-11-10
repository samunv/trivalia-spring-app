package com.trivalia.trivalia.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trivalia.trivalia.dto.CategoriaDTO;
import com.trivalia.trivalia.services.CategoriaService;

@RestController
@RequestMapping("api/categorias")

public class CategoriaController {

    private final CategoriaService cs;

    public CategoriaController(CategoriaService cs) {
        this.cs = cs;

    }

    @GetMapping("/todo")
    public List<CategoriaDTO> obtenerCategorias() {
        return this.cs.obtenerCategorias();
    }

    @GetMapping("/obtener/{id_categoria}")
    public CategoriaDTO obtenerCategoriaInfo(@PathVariable Long id_categoria) {
        return this.cs.obtenerCategoriaPorId(id_categoria);
    }

}
