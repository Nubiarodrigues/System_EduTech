package com.edutech.backend.mapper;

import com.edutech.backend.dtos.ClassesTaughtRequestDTO;
import com.edutech.backend.dtos.ClassesTaughtResponseDTO;
import com.edutech.backend.entities.ClassesTaught;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClassesTaughtMapper {

    ClassesTaught toEntity(ClassesTaughtRequestDTO dto);

    ClassesTaughtResponseDTO toResponseDTO(ClassesTaught classesTaught);

    void updateClassesTaughtFromDTO(ClassesTaughtRequestDTO dto, @MappingTarget ClassesTaught classesTaught);

}
