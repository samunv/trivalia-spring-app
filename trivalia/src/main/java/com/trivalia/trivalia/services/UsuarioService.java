package com.trivalia.trivalia.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.trivalia.trivalia.dto.UsuarioDTO;
import com.trivalia.trivalia.entities.PreguntasEntity;
import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.enums.Item;
import com.trivalia.trivalia.mappers.PreguntaMapper;
import com.trivalia.trivalia.mappers.UsuarioMapper;
import com.trivalia.trivalia.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO obtenerOCrearUsuario(UsuarioDTO dto) {
        // Si existe un usuario con ese UID, me devolverá ese usuario y no creará ninguno nuevo
        if (this.usuarioRepository.existsById(dto.getUid())) {
            UsuarioEntity entityExistente = this.usuarioRepository.findById(dto.getUid()).get();
            UsuarioDTO dtoExistente = UsuarioMapper.INSTANCE.toDTO(entityExistente);
            return dtoExistente;
        } else {
            // Si no existe, crea uno nuevo
            UsuarioEntity entity = new UsuarioEntity();
            entity.setFotoURL(dto.getFotoURL());
            entity.setNombre(dto.getNombre());
            entity.setEmail(dto.getEmail());
            entity.setUid(dto.getUid());
            entity.setCantidadPartidasGanadas(0);
            entity.setEstrellas(0);
            entity.setMonedas(250);
            entity.setVidas(5);
            entity.setPreguntasGanadas(new ArrayList<>());
            entity.setRegaloDisponible(false);
            entity.setCantidadPreguntasFalladas(0);

            try {
                UsuarioEntity savedEntity = this.usuarioRepository.save(entity);
                UsuarioDTO dtoCreado = UsuarioMapper.INSTANCE.toDTO(savedEntity);
                return dtoCreado;

            } catch (DataAccessException e) {
                System.err.println("Error al guardar usuario en la BD: " + e.getMessage());
                return null;
            }
        }

    }

    public UsuarioDTO obtenerUsuario(String uid) {
        Optional<UsuarioEntity> usuarioOpt = this.usuarioRepository.findById(uid);
        if (usuarioOpt.isPresent()) {
            UsuarioEntity usuarioEntity = usuarioOpt.get();
            UsuarioDTO usuarioDTO = UsuarioMapper.INSTANCE.toDTO(usuarioEntity);
            return usuarioDTO;
        } else {
            throw new NoSuchElementException("Usuario no encontrado con UID: " + uid);
        }
    }

    public List<UsuarioDTO> obtenerListaUsuarios() {
        List<UsuarioEntity> entityList = this.usuarioRepository.findAll();
        List<UsuarioDTO> dtoList = entityList.stream().map(entity
                -> UsuarioMapper.INSTANCE.toDTO(entity)
        ).toList();

        return dtoList;
    }

    public String actualizarItem(Item claveItem, Integer cantidad, String uid) {
        UsuarioEntity entity = this.usuarioRepository.findById(uid)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con UID: " + uid));

        switch (claveItem) {
            case Item.monedas ->
                entity.setMonedas(cantidad);
            case Item.vidas ->
                entity.setVidas(cantidad);
            case Item.estrellas ->
                entity.setEstrellas(cantidad);
            default ->
                throw new IllegalArgumentException("Clave de ítem no válida: " + claveItem);
        }

        this.usuarioRepository.save(entity);

        return "Usuario " + entity.getNombre() + " actualizado con éxito.";
    }

}
