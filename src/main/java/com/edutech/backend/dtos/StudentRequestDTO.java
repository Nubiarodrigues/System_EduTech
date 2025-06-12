package com.edutech.backend.dtos;

import java.time.LocalDate;

import com.edutech.backend.entities.Classroom;
import com.edutech.backend.entities.Student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentRequestDTO(
		@NotBlank String name,
		@NotNull LocalDate dateBirth,
		@NotBlank String cpf,
		@NotBlank String sus,
		@NotBlank String address,
		@NotBlank String telephone,
		@NotBlank String emailResponsable,
		@NotBlank String motherName,
		@NotBlank String fatherName,
		@NotNull Classroom classroom) 
{}