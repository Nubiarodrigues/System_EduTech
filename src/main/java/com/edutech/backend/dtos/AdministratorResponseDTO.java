package com.edutech.backend.dtos;

import com.edutech.backend.entities.Administrator;
import com.edutech.backend.enuns.RoleUser;

public record AdministratorResponseDTO(Long id, String name, String email, RoleUser role) {

	public AdministratorResponseDTO(Administrator admin) {
		this(admin.getId(), admin.getName(), admin.getEmail(), admin.getRole());
	}
}
