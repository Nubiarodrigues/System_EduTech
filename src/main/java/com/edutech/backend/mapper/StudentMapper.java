package com.edutech.backend.mapper;

import com.edutech.backend.dtos.student.StudentRequestDTO;
import com.edutech.backend.dtos.student.StudentResponseDTO;
import com.edutech.backend.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collections;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {Collections.class, Collectors.class})
public interface StudentMapper {

	@Mapping(target = "frequency", ignore = true)
	Student toEntity(StudentRequestDTO dto);

	@Mapping(target = "bimesters", expression = "java(entity.getBimesters() != null ? entity.getBimesters().stream().map(BimesterGradeResponseDTO::new).collect(Collectors.toList()) : Collections.emptyList())")
	StudentResponseDTO toResponseDTO(Student entity);

	@Mapping(target = "frequency", ignore = true)
	void updateStudentFromDTO(StudentRequestDTO dto, @MappingTarget Student entity);
}
