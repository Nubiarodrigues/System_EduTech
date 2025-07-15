package com.edutech.backend.dtos.operator;

import com.edutech.backend.entities.Operator;
import com.edutech.backend.enuns.RoleUser;

public record OperatorResponseDTO(Long id, String name, String email, RoleUser role, String sector, String registration) {

	public OperatorResponseDTO(Operator operator) {
		this(
				operator.getId(),
				operator.getName(),
				operator.getEmail(),
				operator.getRole(),
				operator.getSector(),
				operator.getRegistration());
	}

}
