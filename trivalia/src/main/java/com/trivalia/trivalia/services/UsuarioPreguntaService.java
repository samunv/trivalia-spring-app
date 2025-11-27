package com.trivalia.trivalia.services;

import com.trivalia.trivalia.entities.PreguntasEntity;
import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.enums.Item;
import com.trivalia.trivalia.enums.Operaciones;
import com.trivalia.trivalia.model.*;
import com.trivalia.trivalia.model.builders.ResultadoPreguntaBuilder;
import com.trivalia.trivalia.services.interfaces.PreguntaServiceInterface;
import com.trivalia.trivalia.services.interfaces.UsuarioLecturaServiceInterface;
import com.trivalia.trivalia.services.interfaces.UsuarioServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioPreguntaService {

    private final UsuarioServiceInterface usuarioService;
    private final UsuarioLecturaServiceInterface usuarioLecturaService;
    private final PreguntaServiceInterface preguntasService;

    public UsuarioPreguntaService(UsuarioServiceInterface usuarioService, PreguntaServiceInterface preguntasService,  UsuarioLecturaServiceInterface usuarioLecturaService) {
        this.usuarioService = usuarioService;
        this.preguntasService = preguntasService;
        this.usuarioLecturaService = usuarioLecturaService;

    }

    public ResultadoPreguntaRespondidaDTO responderPregunta(String uid, RespuestaUsuarioDTO respuestaUsuario) {
        PreguntasEntity preguntaEntity = this.preguntasService.obtenerPregunta(respuestaUsuario.getIdPregunta());
        boolean respuestaEsCorrecta = respuestaUsuario.getRespuestaSeleccionada().equalsIgnoreCase(preguntaEntity.getRespuesta_correcta());

        if (respuestaEsCorrecta) {
            return this.acertarPregunta(uid, preguntaEntity.getDificultad(), respuestaUsuario.getIdPregunta(), respuestaUsuario.getIdCategoria());
        } else {
            return this.fallarPregunta(uid, preguntaEntity.getRespuesta_correcta(), respuestaUsuario.getIdPregunta(), respuestaUsuario.getIdCategoria());
        }

    }

    public ResultadoPreguntaRespondidaDTO acertarPregunta(String uid, PreguntasEntity.Dificultad dificultad, Long idPregunta, Long idCategoria) {
        Integer calculoEstrellas = this.calcularEstrellasSegunDificultad(dificultad);
        if (this.usuarioService.actualizarItem(Item.estrellas, calculoEstrellas, uid, Operaciones.sumar)) {
            this.insertarPreguntaGanada(idPregunta, uid);
            UsuarioDTO usuarioDTO = this.usuarioLecturaService.obtenerUsuario(uid);
            return this.obtenerResultadoPreguntaRespondidaDTO(true, "¡Correcto!", Item.estrellas, calculoEstrellas, usuarioDTO, idPregunta, idCategoria);
        } else {
            return null;
        }
    }

    public ResultadoPreguntaRespondidaDTO fallarPregunta(String uid, String respuestaCorrecta, Long idPregunta, Long idCategoria) {
        Integer vidasRestar = 1;
        if (this.usuarioService.actualizarItem(Item.vidas, vidasRestar, uid, Operaciones.restar)) {

            UsuarioDTO usuarioDTO = this.usuarioLecturaService.obtenerUsuario(uid);
            usuarioDTO.setCantidadPreguntasFalladas(this.usuarioService.actualizarCantidadPreguntasFalladas(uid));

            return this.obtenerResultadoPreguntaRespondidaDTO(false, "¡Incorrecto!", Item.vidas, vidasRestar, usuarioDTO, idPregunta, idCategoria);
        } else {
            return null;
        }
    }

    private ResultadoPreguntaRespondidaDTO obtenerResultadoPreguntaRespondidaDTO(
            boolean esCorrecta,
            String mensaje,
            Item item,
            Integer cantidadAfectada,
            UsuarioDTO usuarioActualizado,
            Long idPregunta,
            Long idCategoria
    ) {

        PreguntaDTO siguientePregunta = this.preguntasService.obtenerSiguientePregunta(idCategoria, idPregunta);

        Integer index = null;

        // Si NO hay siguiente pregunta → fin del cuestionario
        if (siguientePregunta != null) {
            index = this.preguntasService.obtenerPreguntaIndex(
                    siguientePregunta.getIdPregunta(),
                    idCategoria
            );
        }

        return new ResultadoPreguntaBuilder()
                .esCorrecta(esCorrecta)
                .mensaje(mensaje)
                .itemAfectado(item)
                .cantidadItemAfectada(cantidadAfectada)
                .continuar(esCorrecta) // Solo continuar si hay más preguntas
                .usuarioActualizado(usuarioActualizado)
                .siguientePregunta(siguientePregunta)
                .preguntaIndex(index)
                .build();
    }


    private Integer calcularEstrellasSegunDificultad(PreguntasEntity.Dificultad dificultad) {
        return switch (dificultad) {
            case PreguntasEntity.Dificultad.FACIL -> 10;
            case PreguntasEntity.Dificultad.MEDIO -> 20;
            case PreguntasEntity.Dificultad.DIFICIL -> 30;
        };
    }

    public Long insertarPreguntaGanada(Long idPregunta, String uid) {

        UsuarioEntity usuario = this.usuarioLecturaService.obtenerUsuarioEntity(uid);

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
