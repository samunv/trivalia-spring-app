package com.trivalia.trivalia.services;

import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.enums.Item;
import com.trivalia.trivalia.enums.Operaciones;
import com.trivalia.trivalia.mappers.UsuarioMapper;
import com.trivalia.trivalia.model.PreguntaDTO;
import com.trivalia.trivalia.model.RespuestaUsuarioDTO;
import com.trivalia.trivalia.model.UsuarioDTO;
import com.trivalia.trivalia.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {

    private final UsuarioService usuarioService;
    private final PreguntasService preguntasService;

    public PartidaService(UsuarioService usuarioService, PreguntasService preguntasService) {
        this.usuarioService = usuarioService;
        this.preguntasService = preguntasService;
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
        return this.usuarioService.descontarMonedas(uid, monedasRequeridas);
    }

    public boolean ganarPartida(String uid, PreguntaDTO preguntaDTO) {
        if (this.verificarIndexPregunta(preguntaDTO.getIdPregunta())) {
            UsuarioEntity usuarioEntity = this.usuarioService.obtenerUsuarioEntity(uid);
            this.usuarioService.anadirPartidaGanada(usuarioEntity);
            this.usuarioService.actualizarRegaloDisponible(uid, true);
            this.usuarioService.actualizarItem(Item.monedas, 100, uid, Operaciones.sumar);
            return true;
        }
        return false;

    }

    public boolean verificarIndexPregunta(Long idPregunta) {
        return this.preguntasService.verificarIndexPregunta(idPregunta);
    }


}
