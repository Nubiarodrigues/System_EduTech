package com.edutech.backend.dtos;

import java.time.LocalDate;

import com.edutech.backend.enuns.Situation;

import jakarta.validation.constraints.NotNull;

public record TeacherRequestDTO(
		@NotNull String name,
		@NotNull String cpf,
		@NotNull String telephone,
		Situation status,
		@NotNull LocalDate dateBirth,
		@NotNull Integer workloadTotal,
		String rg,
		String address,
		String matriculation,
		@NotNull String formedCourse
	) {

}
