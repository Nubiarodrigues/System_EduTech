package com.edutech.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.edutech.backend.dtos.ClassroomRequestDTO;
import com.edutech.backend.dtos.ClassroomResponseDTO;
import com.edutech.backend.entities.Classroom;
import com.edutech.backend.entities.Student;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "students", ignore = true)
	Classroom toEntity(ClassroomRequestDTO dto);

	ClassroomResponseDTO toResponseDTO(Classroom entity);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "students", ignore = true)
	void updateClassroomFromDTO(ClassroomRequestDTO dto, @MappingTarget Classroom entity);

}
