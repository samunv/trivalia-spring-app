package com.trivalia.trivalia.services.interfaces;

import com.trivalia.trivalia.model.PreguntaDTO;

public interface PreguntaMemoriaServiceInterface {
    public void guardarPreguntaEnMemoria(PreguntaDTO preguntaDTO);
    public void eliminarPreguntaDeMemoria(Long idPregunta);
    public PreguntaDTO obtenerPreguntaDeMemoria(Long idPregunta);

}
