package com.edutech.backend.dtos;

import com.edutech.backend.entities.Teacher;
import com.edutech.backend.enuns.RoleUser;

public record TeacherResponseDTO(
		Long id,
		String name,
		String email,
		RoleUser role) {

	public TeacherResponseDTO(Teacher teacher) {
		this(
				teacher.getId(),
				teacher.getName(),
				teacher.getEmail(),
				teacher.getRole());
	}
}
