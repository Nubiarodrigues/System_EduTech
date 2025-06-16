package com.edutech.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.edutech.backend.dtos.AdministratorRequestDTO;
import com.edutech.backend.entities.Administrator;

@Mapper(componentModel = "spring")
public interface AdministratorMapper {  
	
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "id", ignore = true)
	Administrator toEntity(AdministratorRequestDTO dto);
	
	
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "id", ignore = true)
	void updateAdministratorFromDTO(AdministratorRequestDTO dto, @MappingTarget Administrator entity);

}
