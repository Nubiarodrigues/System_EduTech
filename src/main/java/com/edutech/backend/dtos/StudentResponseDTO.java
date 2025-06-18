package com.edutech.backend.dtos;

import java.time.LocalDate;

import com.edutech.backend.entities.Student;
import com.edutech.backend.enuns.RoleUser;

public record StudentResponseDTO(
		Long id,
		String name,
		String email,
		RoleUser role,
		LocalDate dateBirth,
		String cpf,
		String motherName,
		String fatherName,
		String registration)
{

	public StudentResponseDTO(Student student) {
		this(
				student.getId(),
				student.getName(),
				student.getEmail(),
				student.getRole(),
				student.getDateBirth(), 
				student.getCpf(), 
				student.getMotherName(),
				student.getFatherName(),
				student.getRegistration()
			);
	}
}
