package com.trivalia.trivalia.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.trivalia.trivalia.entities.UsuarioEntity;
import com.trivalia.trivalia.model.UsuarioDTO;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO toDTO(UsuarioEntity usuario);
}
