package com.trivalia.trivalia.services;

import com.trivalia.trivalia.entities.PreguntasEntity;
import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.enums.Item;
import com.trivalia.trivalia.enums.Operaciones;
import com.trivalia.trivalia.model.RespuestaUsuarioDTO;
import com.trivalia.trivalia.model.ResultadoPreguntaRespondidaDTO;
import com.trivalia.trivalia.model.UsuarioDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioPreguntaService {

    private final UsuarioService usuarioService;
    private final PreguntasService preguntasService;

    public UsuarioPreguntaService(UsuarioService usuarioService, PreguntasService preguntasService) {
        this.usuarioService = usuarioService;
        this.preguntasService = preguntasService;

    }

    public ResultadoPreguntaRespondidaDTO responderPregunta(String uid, RespuestaUsuarioDTO respuestaUsuario) {
        PreguntasEntity preguntaEntity = this.preguntasService.obtenerPregunta(respuestaUsuario.getIdPregunta());
        boolean respuestaEsCorrecta = respuestaUsuario.getRespuestaSeleccionada().equalsIgnoreCase(preguntaEntity.getRespuesta_correcta());

        if (respuestaEsCorrecta) {
            return this.acertarPregunta(uid, preguntaEntity.getDificultad(), respuestaUsuario.getIdPregunta());
        } else {
            return this.fallarPregunta(uid, preguntaEntity.getRespuesta_correcta());
        }

    }

    public ResultadoPreguntaRespondidaDTO acertarPregunta(String uid, PreguntasEntity.Dificultad dificultad, Long idPregunta) {
        Integer calculoEstrellas = this.calcularEstrellasSegunDificultad(dificultad);
        if (this.usuarioService.actualizarItem(Item.estrellas, calculoEstrellas, uid, Operaciones.sumar)) {
            this.insertarPreguntaGanada(idPregunta, uid);
            UsuarioDTO usuarioDTO = this.usuarioService.obtenerUsuario(uid);
            return this.obtenerResultadoPreguntaRespondidaDTO(true, "¡Correcto!", Item.estrellas, calculoEstrellas, usuarioDTO);
        } else {
            return null;
        }
    }

    public ResultadoPreguntaRespondidaDTO fallarPregunta(String uid, String respuestaCorrecta) {
        Integer vidasRestar = 1;
        if (this.usuarioService.actualizarItem(Item.vidas, vidasRestar, uid, Operaciones.restar)) {

            UsuarioDTO usuarioDTO = this.usuarioService.obtenerUsuario(uid);
            usuarioDTO.setCantidadPreguntasFalladas(this.usuarioService.actualizarCantidadPreguntasFalladas(uid));

            return this.obtenerResultadoPreguntaRespondidaDTO(false, "¡Incorrecto! Respuesta correcta: " + respuestaCorrecta, Item.vidas, vidasRestar, usuarioDTO);
        } else {
            return null;
        }
    }

    private ResultadoPreguntaRespondidaDTO obtenerResultadoPreguntaRespondidaDTO(boolean esCorrecta, String mensaje, Item item, Integer cantidadAfectada, UsuarioDTO usuarioActualizado) {
        ResultadoPreguntaRespondidaDTO resultadoPreguntaDTO = new ResultadoPreguntaRespondidaDTO();

        resultadoPreguntaDTO.setEsCorrecta(esCorrecta);
        resultadoPreguntaDTO.setMensaje(mensaje);
        resultadoPreguntaDTO.setItemAfectado(item);
        resultadoPreguntaDTO.setCantidadItemAfectada(cantidadAfectada);
        resultadoPreguntaDTO.setContinuar(esCorrecta);
        resultadoPreguntaDTO.setUsuarioActualizado(usuarioActualizado);
        return resultadoPreguntaDTO;
    }

    private Integer calcularEstrellasSegunDificultad(PreguntasEntity.Dificultad dificultad) {
        return switch (dificultad) {
            case PreguntasEntity.Dificultad.FACIL -> 10;
            case PreguntasEntity.Dificultad.MEDIO -> 20;
            case PreguntasEntity.Dificultad.DIFICIL -> 30;
        };
    }

    public Long insertarPreguntaGanada(Long idPregunta, String uid) {

        UsuarioEntity usuario = this.usuarioService.obtenerUsuarioEntity(uid);

        PreguntasEntity pregunta = this.preguntasService.obtenerPregunta(idPregunta);

        if (pregunta == null) {
            return null; // o lanzar excepción
        }

        // Obtener la lista de preguntas ganadas
        List<PreguntasEntity> preguntasGanadas = usuario.getPreguntasGanadas();

        // Evitar duplicados
        if (!preguntasGanadas.contains(pregunta)) {
            preguntasGanadas.add(pregunta);
            usuario.setPreguntasGanadas(preguntasGanadas);
            this.usuarioService.guardarUsuario(usuario);
        }

        return pregunta.getIdPregunta();
    }
}
