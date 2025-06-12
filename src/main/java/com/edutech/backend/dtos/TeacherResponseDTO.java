package com.edutech.backend.dtos;

import java.time.LocalDate;

import com.edutech.backend.entities.Teacher;

public record TeacherResponseDTO(
		Long id,
		String name,
		LocalDate dateBirth,
		Integer workloadTotal,
		String matriculation) {

	public TeacherResponseDTO(Teacher teacher) {
		this(
				teacher.getId(),
				teacher.getName(),
				teacher.getDateBirth(),
				teacher.getWorkloadTotal(),
				teacher.getMatriculation());
	}
}
