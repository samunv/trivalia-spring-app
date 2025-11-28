package com.trivalia.trivalia.services.interfaces;

import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.enums.Item;
import com.trivalia.trivalia.enums.Operaciones;
import com.trivalia.trivalia.model.Regalo;

public interface RegaloServiceInterface {
    public Regalo obtenerRegalo(String uid);

    public boolean verificarRegaloDisponible(String uid);

    public void actualizarItemUsuario(Item item, Integer cantidad, String uid, Operaciones operacion);

    public void actualizarDisponibilidadRegaloUsuario(String uid);
}
