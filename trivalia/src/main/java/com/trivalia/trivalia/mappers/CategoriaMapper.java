package com.trivalia.trivalia.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.trivalia.trivalia.entities.CategoriaEntity;
import com.trivalia.trivalia.model.CategoriaDTO;

@Mapper
public interface CategoriaMapper {

    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    CategoriaDTO toDTO(CategoriaEntity categoria);
}
