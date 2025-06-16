package com.edutech.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.edutech.backend.dtos.OperatorRequestDTO;
import com.edutech.backend.entities.Operator;

@Mapper(componentModel = "spring")
public interface OperatorMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "role", ignore = true)
	Operator toEntity(OperatorRequestDTO dto);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "role", ignore = true)
	void updateOperatorFromDTO(OperatorRequestDTO dto, @MappingTarget Operator entity);
}
