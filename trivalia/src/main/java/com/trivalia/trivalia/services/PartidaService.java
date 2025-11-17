package com.trivalia.trivalia.services;

import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.model.UsuarioDTO;
import com.trivalia.trivalia.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {

    private final UsuarioService usuarioService;

    public PartidaService(UsuarioService usuarioService) {

        this.usuarioService = usuarioService;
    }

    public boolean jugarPartida(String uid) {
        return this.comprobarVidasUsuario(uid);
    }


    private boolean comprobarVidasUsuario(String uid) {
        com.trivalia.trivalia.entities.UsuarioEntity usuarioEntity = this.usuarioService.obtenerUsuarioEntity(uid);
        if (usuarioEntity.getVidas() >= 1) {
            return true;
        }
        return false;
    }

    public boolean continuarConMonedas(String uid, Integer monedasRequeridas) {
        if (this.usuarioService.descontarMonedas(uid, monedasRequeridas)) {
            return true;
        }
        return false;
    }

    public boolean ganarPartida(String uid) {
        UsuarioEntity usuarioEntity = this.usuarioService.obtenerUsuarioEntity(uid);
        this.usuarioService.anadirPartidaGanada(usuarioEntity);
        this.usuarioService.actualizarRegaloDisponible(uid, true);
        return true;
    }


}
