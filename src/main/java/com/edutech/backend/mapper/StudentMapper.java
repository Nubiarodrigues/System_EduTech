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

	Student toEntity(StudentRequestDTO dto);

	@Mapping(target = "bimesters", expression = "java(entity.getBimesters() != null ? entity.getBimesters().stream().map(BimesterGradeResponseDTO::new).collect(Collectors.toList()) : Collections.emptyList())")
	@Mapping(target = "history", expression = "java(entity.getDisciplinaryHistory() != null ? entity.getDisciplinaryHistory().stream().map(DisciplinaryRecordResponseDTO::new).collect(Collectors.toList()) : Collections.emptyList())")
	@Mapping(target = "frequencies", expression = "java(entity.getFrequencyHistory() != null ? entity.getFrequencyHistory().stream().map(FrequencyResponseDTO::new).collect(Collectors.toList()) : Collections.emptyList())")
	StudentResponseDTO toResponseDTO(Student entity);

	void updateStudentFromDTO(StudentRequestDTO dto, @MappingTarget Student entity);
}
