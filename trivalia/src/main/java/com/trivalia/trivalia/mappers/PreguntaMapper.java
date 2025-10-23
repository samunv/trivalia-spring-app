package com.trivalia.trivalia.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.trivalia.trivalia.dto.PreguntaDTO;
import com.trivalia.trivalia.entities.PreguntasEntity;

@Mapper
public interface PreguntaMapper {

    PreguntaMapper INSTANCE = Mappers.getMapper(PreguntaMapper.class);

    PreguntaDTO toDTO(PreguntasEntity pregunta);
}
