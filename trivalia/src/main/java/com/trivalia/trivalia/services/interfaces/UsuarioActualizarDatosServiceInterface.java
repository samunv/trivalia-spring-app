package com.trivalia.trivalia.services.interfaces;

import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.enums.Item;
import com.trivalia.trivalia.enums.Operaciones;
import com.trivalia.trivalia.model.UsuarioDTO;

public interface UsuarioActualizarDatosServiceInterface {
    boolean actualizarItem(Item item, Integer cantidad, String uid, Operaciones operacion);

    String actualizarRegaloDisponible(String uid, boolean disponible);

    boolean descontarMonedas(String uid, Integer monedasRequeridas);

    UsuarioDTO actualizarNombreFoto(String uid, UsuarioDTO usuarioDTO);

    Integer actualizarCantidadPreguntasFalladas(String uid);

    void anadirPartidaGanada(UsuarioEntity usuarioEntity);
}
