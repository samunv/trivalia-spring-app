package com.trivalia.trivalia.services.interfaces;

import com.trivalia.trivalia.entities.UsoDeIAEntity;
import com.trivalia.trivalia.model.UsoDeIADTO;

public interface UsoDeIAServiceInterface {
    public void obtenerOGuardarUsoDeIA(String uid);
    public void eliminarUsoDeIA(String uid);
    public boolean verificarUsosRestantes(String uid);
}
