package com.edutech.backend.dtos;

import com.edutech.backend.entities.Administrator;

public record AdministratorResponseDTO(Long id, String name, String email) {

	public AdministratorResponseDTO(Administrator admin) {
		this(admin.getId(), admin.getName(), admin.getEmail());
	}
}
