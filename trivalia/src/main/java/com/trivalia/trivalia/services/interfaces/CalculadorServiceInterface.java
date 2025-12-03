package com.trivalia.trivalia.services.interfaces;

import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.enums.Item;

public interface CalculadorServiceInterface {
    public int calcularCostoIASegunMonedasUsuario(String uid);
    public int calcularRecompensaIASegunMonedasUsuario(String uid);
}
