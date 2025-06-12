package com.edutech.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.edutech.backend.dtos.TeacherRequestDTO;
import com.edutech.backend.dtos.TeacherResponseDTO;
import com.edutech.backend.entities.Teacher;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

	@Mapping(target = "email", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "role", ignore = true)
	Teacher toEntity(TeacherRequestDTO dto);

	TeacherResponseDTO toResponseDTO(Teacher entity);
	
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "role", ignore = true)
	void updateTeacherFromDTO(TeacherRequestDTO dto, @MappingTarget Teacher entity);
}
