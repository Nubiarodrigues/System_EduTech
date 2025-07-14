package com.edutech.backend.dtos;

import com.edutech.backend.enuns.RoleUser;

public record TeacherResponseDTO(
		Long id,
		String name,
		String email,
		RoleUser role,
		Integer workloadAllocated) {
}
