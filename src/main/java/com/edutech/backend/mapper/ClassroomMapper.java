package com.edutech.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.edutech.backend.dtos.ClassroomRequestDTO;
import com.edutech.backend.dtos.ClassroomResponseDTO;
import com.edutech.backend.entities.Classroom;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "students", ignore = true)
	@Mapping(target = "coordinatorClass", ignore = true)
	Classroom toEntity(ClassroomRequestDTO dto);

	@Mapping(source = "coordinatorClass.name", target = "coordinatorName")
	ClassroomResponseDTO toResponseDTO(Classroom classroom);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "students", ignore = true)
	@Mapping(target = "coordinatorClass", ignore = true)
	void updateClassroomFromDTO(ClassroomRequestDTO dto, @MappingTarget Classroom entity);
}
