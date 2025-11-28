package com.trivalia.trivalia.controllers;

import java.util.List;

import com.trivalia.trivalia.services.interfaces.CategoriaServiceInterface;
import org.springframework.web.bind.annotation.*;

import com.trivalia.trivalia.model.CategoriaDTO;

@RestController
@RequestMapping("api/categorias")
public class CategoriaController {

    private final CategoriaServiceInterface cs;

    public CategoriaController(CategoriaServiceInterface cs) {
        this.cs = cs;

    }

    @GetMapping("/todo")
    public List<CategoriaDTO> obtenerCategorias() {
        return this.cs.obtenerCategorias();
    }

    @GetMapping("/obtener/{id_categoria}")
    public CategoriaDTO obtenerCategoriaInfo(@PathVariable Long id_categoria) {
        return this.cs.obtenerCategoriaDTOPorId(id_categoria);
    }


}
