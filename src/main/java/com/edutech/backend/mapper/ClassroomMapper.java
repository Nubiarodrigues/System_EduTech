package com.edutech.backend.mapper;

import com.edutech.backend.dtos.classroom.ClassroomRequestDTO;
import com.edutech.backend.dtos.classroom.ClassroomResponseDTO;
import com.edutech.backend.entities.Classroom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collections;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {Collections.class, Collectors.class})
public interface ClassroomMapper {

	Classroom toEntity(ClassroomRequestDTO dto);

	@Mapping(target = "disciplines", source = "entity.disciplines")
	@Mapping(target = "coordinatorName", expression = "java(entity.getCoordinatorClass() != null ? entity.getCoordinatorClass().getName() : null)")
	@Mapping(target = "schoolNotices", expression = "java(entity.getSchoolNotices() != null ? entity.getSchoolNotices().stream().map(SchoolNoticesResponseDTO::new).collect(Collectors.toList()) : Collections.emptyList())")
	ClassroomResponseDTO toResponseDTO(Classroom entity);

	void updateClassroomFromDTO(ClassroomRequestDTO dto, @MappingTarget Classroom entity);
}
