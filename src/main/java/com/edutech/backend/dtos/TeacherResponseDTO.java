package com.edutech.backend.dtos;

import java.time.LocalDate;

import com.edutech.backend.entities.Teacher;
import com.edutech.backend.enuns.RoleUser;

public record TeacherResponseDTO(
		Long id,
		String name,
		String email,
		RoleUser role,
		LocalDate dateBirth,
		Integer workloadTotal,
		String registration,
		String address) {

	public TeacherResponseDTO(Teacher teacher) {
		this(
				teacher.getId(),
				teacher.getName(),
				teacher.getEmail(),
				teacher.getRole(),
				teacher.getDateBirth(),
				teacher.getWorkloadTotal(),
				teacher.getRegistration(),
				teacher.getAddress());
	}
}
