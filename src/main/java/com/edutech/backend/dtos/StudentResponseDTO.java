package com.edutech.backend.dtos;

import java.time.LocalDate;

import com.edutech.backend.entities.Student;

import jakarta.validation.constraints.NotNull;

public record StudentResponseDTO(
		Long id,
		@NotNull String name,
		LocalDate dateBirth,
		@NotNull String cpf,
		String motherName,
		String fatherName)
{

	public StudentResponseDTO(Student student) {
		this(
				student.getId(),
				student.getName(),
				student.getDateBirth(), 
				student.getCpf(), 
				student.getMotherName(),
				student.getFatherName()				
			);
	}
}
