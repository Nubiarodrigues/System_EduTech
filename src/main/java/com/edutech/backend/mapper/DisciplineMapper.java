package com.edutech.backend.mapper;

import com.edutech.backend.dtos.discipline.DisciplineRequestDTO;
import com.edutech.backend.dtos.discipline.DisciplineResponseDTO;
import com.edutech.backend.entities.Discipline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collections;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {Collectors.class, Collections.class})
public interface DisciplineMapper {

    Discipline toEntity(DisciplineRequestDTO dto);

    @Mapping(target = "teacherName", expression = "java(entity.getTeacher() != null ? entity.getTeacher().getName() : null)")
    @Mapping(target = "classesTaught", expression = "java(entity.getClassesTaught() != null ? entity.getClassesTaught().stream().map(ClassesTaughtResponseDTO::new).collect(Collectors.toList()) : Collections.emptyList())")
    DisciplineResponseDTO toResponseDTO(Discipline entity);

    void updateDisciplineFromDTO(DisciplineRequestDTO dto, @MappingTarget Discipline entity);
}
