package com.trivalia.trivalia.services;

import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {

    private final UsuarioRepository usuarioRepository;

    public PartidaService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean jugarPartida(String uid) {
        return this.comprobarVidasUsuario(uid);
    }



    private boolean comprobarVidasUsuario(String uid) {
        if (this.usuarioRepository.findById(uid).isPresent()) {
            UsuarioEntity usuarioEntity = this.usuarioRepository.findById(uid).get();
            if (usuarioEntity.getVidas() >= 1) {
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean continuarConMonedas(String uid, Integer monedasRequeridas) {

        UsuarioEntity usuarioEntity = this.usuarioRepository.findById(uid).orElse(null);

        if (usuarioEntity == null) {
            return false;
        };
        Integer monedasUsuario = usuarioEntity.getMonedas();

        if (monedasRequeridas <= monedasUsuario) {
            usuarioEntity.setMonedas(monedasUsuario - monedasRequeridas);
            this.usuarioRepository.save(usuarioEntity);
            return true;
        }

        return false;
    }
}
