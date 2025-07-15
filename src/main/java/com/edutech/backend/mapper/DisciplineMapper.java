package com.edutech.backend.mapper;

import com.edutech.backend.dtos.discipline.DisciplineRequestDTO;
import com.edutech.backend.dtos.discipline.DisciplineResponseDTO;
import com.edutech.backend.entities.Discipline;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DisciplineMapper {

    Discipline toEntity(DisciplineRequestDTO dto);

    DisciplineResponseDTO toResponseDTO(Discipline discipline);

    void updateDisciplineFromDTO(DisciplineRequestDTO dto, @MappingTarget Discipline entity);
}
