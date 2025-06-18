package com.edutech.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.edutech.backend.dtos.AdministratorRequestDTO;
import com.edutech.backend.dtos.AdministratorResponseDTO;
import com.edutech.backend.entities.Administrator;
import com.edutech.backend.entities.Classroom;

@Mapper(componentModel = "spring")
public interface AdministratorMapper {

	Administrator toEntity(AdministratorRequestDTO dto);

	AdministratorResponseDTO toResponseDTO(Administrator administrator);

	void updateAdministratorFromDTO(AdministratorRequestDTO dto, @MappingTarget Administrator entity);

}
