package com.trivalia.trivalia.services.interfaces;

import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.model.UsuarioDTO;

public interface UsuarioGuardarServiceInterface {
    UsuarioDTO obtenerOCrearUsuario(UsuarioDTO dto);
    UsuarioDTO guardarUsuario(UsuarioEntity entity);
}
