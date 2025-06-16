package com.edutech.backend.dtos;

import jakarta.validation.constraints.NotNull;

public record OperatorRequestDTO(
		@NotNull String name,
		@NotNull String sector) {

}
