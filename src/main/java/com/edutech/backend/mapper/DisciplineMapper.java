package com.edutech.backend.mapper;

import com.edutech.backend.dtos.DisciplineRequestDTO;
import com.edutech.backend.entities.Discipline;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DisciplineMapper {

    Discipline toEntity(DisciplineRequestDTO dto);

    void updateDisciplineFromDTO(DisciplineRequestDTO dto, @MappingTarget Discipline entity);
}
