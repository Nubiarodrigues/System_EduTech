package com.edutech.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.edutech.backend.dtos.coordinator.CoordinatorRequestDTO;
import com.edutech.backend.dtos.coordinator.CoordinatorResponseDTO;
import com.edutech.backend.entities.Coordinator;

@Mapper(componentModel = "spring")
public interface CoordinatorMapper {

	Coordinator toEntity(CoordinatorRequestDTO dto);

	CoordinatorResponseDTO toResponseDTO(Coordinator entity);

	void updateCoordinatorFromDTO(CoordinatorRequestDTO dto, @MappingTarget Coordinator entity);
}
