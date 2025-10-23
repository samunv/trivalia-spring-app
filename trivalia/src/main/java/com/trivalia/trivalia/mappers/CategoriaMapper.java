package com.trivalia.trivalia.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.trivalia.trivalia.dto.CategoriaDTO;
import com.trivalia.trivalia.entities.CategoriaEntity;

@Mapper
public interface CategoriaMapper {

    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    CategoriaDTO toDTO(CategoriaEntity categoria);
}
