package com.edutech.backend.dtos.operator;

import com.edutech.backend.enuns.RoleUser;

import jakarta.validation.constraints.NotNull;

public record OperatorRequestDTO(
		@NotNull String name,
		@NotNull String email,
		@NotNull RoleUser role,
		@NotNull String password,
		@NotNull String sector) {

}
