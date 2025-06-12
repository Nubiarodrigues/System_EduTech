package com.edutech.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.edutech.backend.dtos.StudentRequestDTO;
import com.edutech.backend.dtos.StudentResponseDTO;
import com.edutech.backend.entities.Student;
import com.edutech.backend.entities.Teacher;

@Mapper(componentModel = "spring")
public interface StudentMapper {

	@Mapping(target = "email", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "frequency", ignore = true)
	Student toEntity(StudentRequestDTO dto);

	StudentResponseDTO toResponseDTO(Student entity);

	@Mapping(target = "email", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "frequency", ignore = true)
	void updateStudentFromDTO(StudentRequestDTO dto, @MappingTarget Student entity);
}
