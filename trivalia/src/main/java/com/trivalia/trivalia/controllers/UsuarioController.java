package com.trivalia.trivalia.controllers;

import java.util.List;

import com.trivalia.trivalia.services.interfaces.UsuarioLecturaServiceInterface;
import com.trivalia.trivalia.services.interfaces.UsuarioServiceInterface;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trivalia.trivalia.model.UsuarioDTO;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioServiceInterface usuarioService;
    private final UsuarioLecturaServiceInterface usuarioLecturaService;

    public UsuarioController(UsuarioServiceInterface usuarioService,  UsuarioLecturaServiceInterface usuarioLecturaService) {
        this.usuarioService = usuarioService;
        this.usuarioLecturaService = usuarioLecturaService;
    }

    @PostMapping("/crear")
    public UsuarioDTO crearUsuario(@RequestBody UsuarioDTO dto) {
        return this.usuarioService.obtenerOCrearUsuario(dto);
    }

    @PreAuthorize("#uid == authentication.name")
    @GetMapping("/obtener/{uid}")
    public UsuarioDTO obtenerUsuario(@PathVariable String uid) {
        return this.usuarioLecturaService.obtenerUsuario(uid);
    }

    @GetMapping("/listar/{limite}")
    public List<UsuarioDTO> listarUsuarios(@PathVariable Integer limite) {
        return this.usuarioLecturaService.obtenerListaUsuarios(limite);
    }

    // @PreAuthorize para verificar que el uid enviado coincide con el que está guardado en el de SpringSecurity
    @PreAuthorize("#uid == authentication.name")
    @PatchMapping("/actualizar-nombre-foto/{uid}")
    public UsuarioDTO actualizarUsuario(@PathVariable String uid, @RequestBody UsuarioDTO dto) {

        return this.usuarioService.actualizarNombreFoto(uid, dto);
    }
}
