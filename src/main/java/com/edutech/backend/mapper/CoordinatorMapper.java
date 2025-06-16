package com.edutech.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.edutech.backend.dtos.CoordinatorRequestDTO;
import com.edutech.backend.dtos.CoordinatorResponseDTO;
import com.edutech.backend.entities.Coordinator;

@Mapper(componentModel = "spring")
public interface CoordinatorMapper {

	@Mapping(target = "email", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "classroomsModality", ignore = true)
	Coordinator toEntity(CoordinatorRequestDTO dto);
	
	CoordinatorResponseDTO toResponseDTO(Coordinator entity);
	
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "classroomsModality", ignore = true)
	void updateCoordinatorFromDTO(CoordinatorRequestDTO dto, @MappingTarget Coordinator entity);
}
