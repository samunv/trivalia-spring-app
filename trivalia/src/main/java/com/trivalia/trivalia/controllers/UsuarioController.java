package com.trivalia.trivalia.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.trivalia.trivalia.dto.UsuarioDTO;
import com.trivalia.trivalia.services.UsuarioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trivalia.trivalia.enums.Item;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/crear")
    public UsuarioDTO crearUsuario(@RequestBody UsuarioDTO dto) {
        return this.usuarioService.obtenerOCrearUsuario(dto);
    }

    @GetMapping("/obtener/{uid}")
    public UsuarioDTO verificarUsuario(@PathVariable String uid) {
        return this.usuarioService.obtenerUsuario(uid);
    }

    @GetMapping("/listar")
    public List<UsuarioDTO> listarUsuarios() {
        return this.usuarioService.obtenerListaUsuarios();
    }

    @PatchMapping("/actualizar-item/{uid}/{claveItem}/{numero}")
    public Map<String, String> actualizarItems(
            @PathVariable String uid,
            @PathVariable Item claveItem,
            @PathVariable Integer numero
    ) {
        return Map.of("exito", this.usuarioService.actualizarItem(claveItem, numero, uid));
    }
}
