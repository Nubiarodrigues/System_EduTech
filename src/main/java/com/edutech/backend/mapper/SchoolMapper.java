package com.edutech.backend.mapper;

import com.edutech.backend.dtos.school.SchoolRequestDTO;
import com.edutech.backend.dtos.school.SchoolRequestSimpleDTO;
import com.edutech.backend.dtos.school.SchoolResponseDTO;
import com.edutech.backend.entities.School;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SchoolMapper {

    School toEntity(SchoolRequestDTO dto);

    @Mapping(target = "telephone", source = "entity.telephone")
    @Mapping(target = "stages", source = "entity.stages")
    SchoolResponseDTO toResponseDTO(School entity);

    void updateSchoolFromDTO(SchoolRequestDTO dto, @MappingTarget School school);

    void partialSchoolFromDTO(SchoolRequestSimpleDTO dto, @MappingTarget School entity);
}
