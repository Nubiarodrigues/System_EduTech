package com.edutech.backend.dtos.coordinator;

import java.time.LocalDate;

import com.edutech.backend.enuns.RoleUser;
import com.edutech.backend.enuns.Situation;
import com.edutech.backend.enuns.TeachingState;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

public record CoordinatorRequestDTO(
		@NotNull String name,
		@NotNull String email,
		@NotNull RoleUser role,
		@NotNull String password,
		@NotNull String cpf,
		String telephone,
		Situation status,

		@JsonFormat(pattern = "dd-MM-yyyy")
		@NotNull
		LocalDate dateBirth,

		String rg,
		@NotNull String cep,
		@NotNull String formedCourse,
		@NotNull TeachingState modality
) {

}
