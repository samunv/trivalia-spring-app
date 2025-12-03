package com.trivalia.trivalia.services;

import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.enums.Item;
import com.trivalia.trivalia.services.interfaces.CalculadorServiceInterface;
import com.trivalia.trivalia.services.interfaces.UsuarioLecturaServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class CalculadorService implements CalculadorServiceInterface {
    private final UsuarioLecturaServiceInterface usuarioLecturaService;

    public CalculadorService(UsuarioLecturaServiceInterface usuarioLecturaService) {
        this.usuarioLecturaService = usuarioLecturaService;
    }

    @Override
    public int calcularCostoIASegunMonedasUsuario(String uid) {
        UsuarioEntity usuarioEntity = this.usuarioLecturaService.obtenerUsuarioEntity(uid);
        if (usuarioEntity.getMonedas() < 1000) {
            return 300;
        }
        if(usuarioEntity.getMonedas() >=1000 && usuarioEntity.getMonedas() <=4000){
            return 650;
        }
        if(usuarioEntity.getMonedas() >=5000){
            return (int) Math.round(usuarioEntity.getMonedas() / 4.0);
        }
        return 0;
    }

    @Override
    public int calcularRecompensaIASegunMonedasUsuario(String uid) {
        UsuarioEntity usuarioEntity = this.usuarioLecturaService.obtenerUsuarioEntity(uid);
        if (usuarioEntity.getMonedas() < 1000) {
            return 400;
        }
        if(usuarioEntity.getMonedas() >=1000 && usuarioEntity.getMonedas() <=4000){
            return 750;
        }
        if(usuarioEntity.getMonedas() >=5000){
            return (int) Math.round(usuarioEntity.getMonedas() / 4.0)+500;
        }
        return 0;
    }
}
