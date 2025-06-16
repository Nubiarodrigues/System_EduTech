package com.edutech.backend.dtos;

import jakarta.validation.constraints.NotNull;

public record AdministratorRequestDTO(
		@NotNull String name,
		@NotNull String email
		) {

}
