package com.edutech.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.edutech.backend.dtos.OperatorRequestDTO;
import com.edutech.backend.dtos.OperatorResponseDTO;
import com.edutech.backend.entities.Operator;

@Mapper(componentModel = "spring")
public interface OperatorMapper {

	Operator toEntity(OperatorRequestDTO dto);

	OperatorResponseDTO toResponseDTO(Operator entity);

	void updateOperatorFromDTO(OperatorRequestDTO dto, @MappingTarget Operator entity);
}
