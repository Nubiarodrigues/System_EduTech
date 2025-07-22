package com.edutech.backend.dtos.teacher;

import java.time.LocalDate;

import com.edutech.backend.enuns.RoleUser;
import com.edutech.backend.enuns.Situation;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

public record TeacherRequestDTO(
		@NotNull String name,
		@NotNull String cpf,
		@NotNull String email,
		@NotNull RoleUser role,
		@NotNull String password,
		@NotNull String telephone,
		Situation status,

		@JsonFormat(pattern = "dd-MM-yyyy")
		@NotNull
		LocalDate dateBirth,

		@NotNull Integer workloadTotal,
		String rg, String cep,
		@NotNull String formedCourse) 
{}
