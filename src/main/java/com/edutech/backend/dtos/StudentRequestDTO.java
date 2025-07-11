package com.edutech.backend.dtos;

import com.edutech.backend.enuns.RoleUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record StudentRequestDTO(
		@NotBlank String name,

		@JsonFormat(pattern = "dd-MM-yyyy")
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