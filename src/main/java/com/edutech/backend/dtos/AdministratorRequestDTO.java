package com.edutech.backend.dtos;

import com.edutech.backend.enuns.RoleUser;

import jakarta.validation.constraints.NotNull;

public record AdministratorRequestDTO(
		@NotNull String name,
		@NotNull String email,
		@NotNull RoleUser role,
		@NotNull String password) {

}
