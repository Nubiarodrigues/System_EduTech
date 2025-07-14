package com.edutech.backend.mapper;

import com.edutech.backend.dtos.TeacherRequestDTO;
import com.edutech.backend.dtos.TeacherResponseDTO;
import com.edutech.backend.entities.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

	Teacher toEntity(TeacherRequestDTO dto);

	TeacherResponseDTO toResponseDTO(Teacher entity);

	void updateTeacherFromDTO(TeacherRequestDTO dto, @MappingTarget Teacher entity);
}
