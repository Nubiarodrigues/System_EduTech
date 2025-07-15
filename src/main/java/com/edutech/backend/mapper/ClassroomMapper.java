package com.edutech.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.edutech.backend.dtos.classroom.ClassroomRequestDTO;
import com.edutech.backend.dtos.classroom.ClassroomResponseDTO;
import com.edutech.backend.entities.Classroom;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {

	Classroom toEntity(ClassroomRequestDTO dto);

	ClassroomResponseDTO toResponseDTO(Classroom classroom);

	void updateClassroomFromDTO(ClassroomRequestDTO dto, @MappingTarget Classroom entity);
}
