package com.trivalia.trivalia.services;

import com.trivalia.trivalia.model.PreguntaDTO;
import com.trivalia.trivalia.services.interfaces.PreguntaMemoriaServiceInterface;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class PreguntaMemoriaService implements PreguntaMemoriaServiceInterface {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final Duration duracionEnCache = Duration.ofSeconds(30);

    public PreguntaMemoriaService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;

    }

    @Override
    public void guardarPreguntaEnMemoria(@NotNull PreguntaDTO preguntaDTO) {
        String key = "idPregunta:" + preguntaDTO.getIdPregunta();
        // Guardar en Redis con expiración
        this.redisTemplate.opsForValue().set(key, preguntaDTO, duracionEnCache);
    }

    @Override
    public void eliminarPreguntaDeMemoria(Long idPregunta) {
        String key = "idPregunta:" + idPregunta;
        redisTemplate.delete(key);
    }

    @Override
    public PreguntaDTO obtenerPreguntaDeMemoria(Long idPregunta) {
        if (idPregunta == null) return null;
        String key = "idPregunta:" + idPregunta;
        Object obj = redisTemplate.opsForValue().get(key);
        if (obj instanceof PreguntaDTO) {
            return (PreguntaDTO) obj;
        }
        return null;
    }

}
