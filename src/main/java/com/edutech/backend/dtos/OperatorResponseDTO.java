package com.edutech.backend.dtos;

import com.edutech.backend.entities.Operator;

public record OperatorResponseDTO(Long id, String name, String sector) {

	public OperatorResponseDTO(Operator operator) {
		this(operator.getId(), operator.getName(), operator.getSector());
	}

}
