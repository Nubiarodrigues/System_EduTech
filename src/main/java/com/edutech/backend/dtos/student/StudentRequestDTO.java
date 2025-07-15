package com.edutech.backend.dtos.student;

import com.edutech.backend.enuns.RoleUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record StudentRequestDTO(
		@NotBlank String name,

		@NotNull
		LocalDate dateBirth,

		@NotBlank String cpf,
		@NotNull RoleUser role,
		@NotNull String email,
		@NotNull String password,
		@NotBlank String sus,
		@NotBlank String cep,
		@NotBlank String telephone,
		@NotBlank String emailResponsable,
		@NotBlank String motherName,
		@NotBlank String fatherName,
		@NotNull Long classroomId) 
{}