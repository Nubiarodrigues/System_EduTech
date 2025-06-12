package com.edutech.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.edutech.backend.dtos.ClassroomRequestDTO;
import com.edutech.backend.entities.Classroom;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "students", ignore = true)
	Classroom toEntity(ClassroomRequestDTO dto);

	ClassroomRequestDTO toDTO(Classroom entity);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "students", ignore = true)
	void updateClassroomFromDTO(ClassroomRequestDTO dto, @MappingTarget Classroom entity);

}
