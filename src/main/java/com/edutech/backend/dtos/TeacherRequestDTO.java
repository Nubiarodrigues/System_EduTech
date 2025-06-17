package com.edutech.backend.dtos;

import java.time.LocalDate;

import com.edutech.backend.enuns.RoleUser;
import com.edutech.backend.enuns.Situation;

import jakarta.validation.constraints.NotNull;

public record TeacherRequestDTO(
		@NotNull String name,
		@NotNull String cpf,
		@NotNull String email,
		@NotNull RoleUser role,
		@NotNull String password,
		@NotNull String telephone,
		Situation status,
		@NotNull LocalDate dateBirth,
		@NotNull Integer workloadTotal,
		String rg, String address,
		@NotNull String formedCourse) 
{}
