package com.edutech.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.edutech.backend.dtos.TeacherRequestDTO;
import com.edutech.backend.dtos.TeacherResponseDTO;
import com.edutech.backend.entities.Teacher;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

	Teacher toEntity(TeacherRequestDTO dto);

	TeacherResponseDTO toResponseDTO(Teacher entity);

	void updateTeacherFromDTO(TeacherRequestDTO dto, @MappingTarget Teacher entity);
}
