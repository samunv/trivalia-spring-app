package com.trivalia.trivalia.services;

import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.enums.Item;
import com.trivalia.trivalia.enums.Operaciones;
import com.trivalia.trivalia.model.PreguntaDTO;
import com.trivalia.trivalia.model.RespuestaUsuarioDTO;
import com.trivalia.trivalia.model.ResultadoPreguntaRespondidaDTO;
import com.trivalia.trivalia.services.interfaces.PreguntaServiceInterface;
import com.trivalia.trivalia.services.interfaces.UsuarioLecturaServiceInterface;
import com.trivalia.trivalia.services.interfaces.UsuarioServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {

    private final UsuarioServiceInterface usuarioService;
    private final UsuarioLecturaServiceInterface usuarioLecturaService;
    private final PreguntaServiceInterface preguntasService;
    private final UsuarioPreguntaService usuarioPreguntaService;

    public PartidaService(UsuarioServiceInterface usuarioService, UsuarioLecturaServiceInterface usuarioLecturaService ,PreguntaServiceInterface preguntasService, UsuarioPreguntaService usuarioPreguntaService) {
        this.usuarioService = usuarioService;
        this.usuarioLecturaService = usuarioLecturaService;
        this.preguntasService = preguntasService;
        this.usuarioPreguntaService = usuarioPreguntaService;
    }

    public boolean jugarPartida(String uid) {
        return this.comprobarVidasUsuario(uid);
    }


    private boolean comprobarVidasUsuario(String uid) {
        com.trivalia.trivalia.entities.UsuarioEntity usuarioEntity = this.usuarioLecturaService.obtenerUsuarioEntity(uid);
        if (usuarioEntity.getVidas() >= 1) {
            return true;
        }
        return false;
    }

    public boolean continuarConMonedas(String uid, Integer monedasRequeridas) {
        return this.usuarioService.descontarMonedas(uid, monedasRequeridas);
    }

    public boolean ganarPartida(String uid, PreguntaDTO preguntaDTO) {
        if (this.preguntasService.esUltimaPreguntaDeCategoria(preguntaDTO.getIdPregunta())) {
            UsuarioEntity usuarioEntity = this.usuarioLecturaService.obtenerUsuarioEntity(uid);
            this.usuarioService.anadirPartidaGanada(usuarioEntity);
            this.usuarioService.actualizarRegaloDisponible(uid, true);
            this.usuarioService.actualizarItem(Item.monedas, 100, uid, Operaciones.sumar);
            return true;
        }
        return false;

    }


    public PreguntaDTO obtenerPrimeraPregunta(Long idCategoria) {
        return  this.preguntasService.obtenerPrimeraPregunta(idCategoria);
    }

    public ResultadoPreguntaRespondidaDTO responderPregunta(String uid, RespuestaUsuarioDTO respuestaUsuario){
        return this.usuarioPreguntaService.responderPregunta(uid, respuestaUsuario);
    }



}
