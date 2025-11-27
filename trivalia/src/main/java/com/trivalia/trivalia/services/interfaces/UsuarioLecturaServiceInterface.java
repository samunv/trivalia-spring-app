package com.trivalia.trivalia.services.interfaces;

import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.model.UsuarioDTO;

import java.util.List;

public interface UsuarioLecturaServiceInterface {

    UsuarioEntity obtenerUsuarioEntity(String uid);

    List<UsuarioDTO> obtenerListaUsuarios(Integer limite);

    UsuarioDTO obtenerUsuario(String uid);
}
