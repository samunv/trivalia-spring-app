package com.trivalia.trivalia.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trivalia.trivalia.config.Jwt;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.trivalia.trivalia.services.UsuarioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trivalia.trivalia.enums.Item;
import com.trivalia.trivalia.model.UsuarioDTO;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private Jwt jwt;

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

    @GetMapping("/listar/{limite}")
    public List<UsuarioDTO> listarUsuarios(@PathVariable Integer limite) {
        return this.usuarioService.obtenerListaUsuarios(limite);
    }

    // @PreAuthorize para verificar que el uid enviado coincide con el que está guardado en el de SpringSecurity
    @PreAuthorize("#uid == authentication.name")
    @PatchMapping("/actualizar-nombre-foto/{uid}")
    public UsuarioDTO actualizarUsuario(@PathVariable String uid, @RequestBody UsuarioDTO dto) {

        return this.usuarioService.actualizarNombreFoto(uid, dto);
    }
}
