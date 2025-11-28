package com.trivalia.trivalia.services.interfaces;

import com.trivalia.trivalia.entities.PreguntasEntity;
import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.enums.Item;
import com.trivalia.trivalia.enums.Operaciones;
import com.trivalia.trivalia.model.PreguntaDTO;
import com.trivalia.trivalia.model.RespuestaUsuarioDTO;
import com.trivalia.trivalia.model.ResultadoPreguntaRespondidaDTO;
import com.trivalia.trivalia.model.UsuarioDTO;
import com.trivalia.trivalia.model.builders.ResultadoPreguntaBuilder;

import java.util.List;

public interface UsuarioPreguntaServiceInterface {

    public ResultadoPreguntaRespondidaDTO responderPregunta(String uid, RespuestaUsuarioDTO respuestaUsuario);

    public ResultadoPreguntaRespondidaDTO acertarPregunta(String uid, PreguntasEntity.Dificultad dificultad, Long idPregunta, Long idCategoria);

    public ResultadoPreguntaRespondidaDTO fallarPregunta(String uid, String respuestaCorrecta, Long idPregunta, Long idCategoria);

    public Long insertarPreguntaGanada(Long idPregunta, String uid);
}
