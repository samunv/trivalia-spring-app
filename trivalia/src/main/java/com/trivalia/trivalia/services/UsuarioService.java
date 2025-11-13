package com.trivalia.trivalia.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.trivalia.trivalia.entities.PreguntasEntity;
import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.enums.Item;
import com.trivalia.trivalia.enums.Operaciones;
import com.trivalia.trivalia.mappers.UsuarioMapper;
import com.trivalia.trivalia.model.UsuarioDTO;
import com.trivalia.trivalia.repositories.PreguntasRepository;
import com.trivalia.trivalia.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PreguntasRepository preguntaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PreguntasRepository preguntaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.preguntaRepository = preguntaRepository;
    }

    public UsuarioDTO obtenerOCrearUsuario(UsuarioDTO dto) {
        // Si existe un usuario con ese UID, me devolverá ese usuario y no creará ninguno nuevo
        if (this.usuarioRepository.existsById(dto.getUid())) {
            UsuarioEntity entityExistente = this.usuarioRepository.findById(dto.getUid()).get();
            UsuarioDTO dtoExistente = UsuarioMapper.INSTANCE.toDTO(entityExistente);
            return dtoExistente;
        } else {
            return this.crearNuevoUsuario(dto);
        }

    }

    public UsuarioDTO crearNuevoUsuario(UsuarioDTO dto) {
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
        List<UsuarioDTO> dtoList = entityList.stream().map((entity)
                        -> {
                    UsuarioDTO dto = UsuarioMapper.INSTANCE.toDTO(entity);
                    // Ocultar el UID en la lista de usuarios pública
                    dto.setUid(null);
                    return dto;
                }
        ).toList();

        return dtoList;
    }

    public boolean actualizarItem(Item item, Integer cantidad, String uid, Operaciones operacion) {
        UsuarioEntity entity = this.usuarioRepository.findById(uid)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con UID: " + uid));

        switch (item) {
            case monedas -> {
                switch (operacion) {
                    case sumar -> entity.setMonedas(entity.getMonedas() + cantidad);
                    case restar -> entity.setMonedas(entity.getMonedas() - cantidad);
                }
            }
            case estrellas -> {
                switch (operacion) {
                    case sumar -> entity.setEstrellas(entity.getEstrellas() + cantidad);
                    case restar -> entity.setEstrellas(entity.getEstrellas() - cantidad);
                }
            }
            case vidas -> {
                switch (operacion) {
                    case sumar -> entity.setVidas(entity.getVidas() + cantidad);
                    case restar -> entity.setVidas(entity.getVidas() - cantidad);
                }
            }
        }

        this.usuarioRepository.save(entity);

        return true;
    }

    public boolean verificarRegaloDisponible(String uid) {
        UsuarioEntity entity = this.usuarioRepository.findById(uid)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con UID: " + uid));

        return entity.getRegaloDisponible();
    }

    public String actualizarRegaloDisponible(String uid, boolean disponible) {
        UsuarioEntity entity = this.usuarioRepository.findById(uid)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con UID: " + uid));

        entity.setRegaloDisponible(disponible);
        this.usuarioRepository.save(entity);

        return "Estado de regalo disponible actualizado a " + disponible + " para el usuario " + entity.getNombre();
    }

    public String actualizarPreguntasGanadas(String uid, Long idPregunta) {

        PreguntasEntity preguntaEntity = this.preguntaRepository.findById(idPregunta).get();
        UsuarioEntity usuarioEntity = this.usuarioRepository.findById(uid).get();

        return "";
    }

    public UsuarioDTO actualizarNombreFoto(String uid, UsuarioDTO usuarioDTO) {
        Optional<UsuarioEntity> optionalUsuario = this.usuarioRepository.findById(uid);
        if (optionalUsuario.isPresent()) {
            UsuarioEntity usuarioEntity = optionalUsuario.get();
            usuarioEntity.setNombre(usuarioDTO.getNombre());
            usuarioEntity.setFotoURL(usuarioDTO.getFotoURL());
            UsuarioEntity usuarioActualizado = this.usuarioRepository.save(usuarioEntity);
            UsuarioDTO nuevoDTO = UsuarioMapper.INSTANCE.toDTO(usuarioActualizado);
            System.out.print("Actualizando usuario " + usuarioEntity.getNombre());
            return nuevoDTO;

        } else {
            System.out.print("Error al actualizar usuario");
            return null;
        }

    }

}
