package com.trivalia.trivalia.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trivalia.trivalia.model.CategoriaDTO;
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
        return this.cs.obtenerCategoriaDTOPorId(id_categoria);
    }

    @GetMapping("/jugar/{uid}")
    public Map<String, Boolean> jugarCategoria(@PathVariable String uid) {
        Map<String, Boolean> resultado = new HashMap<>();
        resultado.put("resultado", this.cs.jugarCategoria(uid));
        return resultado;
    }

    @GetMapping("/ganar/{uid}")
    public Map<String, Boolean> ganar(@PathVariable String uid) {
        // TODO: Añadir en servicio, lógica para finalizar la partida de una categoria o x partida
        return Map.of();
    }



}
