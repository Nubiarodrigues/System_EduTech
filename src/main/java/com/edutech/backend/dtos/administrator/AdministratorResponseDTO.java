package com.edutech.backend.dtos.administrator;

import com.edutech.backend.entities.Administrator;
import com.edutech.backend.enuns.RoleUser;

public record AdministratorResponseDTO(Long id, String name, String email, RoleUser role, String registration, Long idSchool) {

	public AdministratorResponseDTO(Administrator admin) {
		this(admin.getId(), admin.getName(), admin.getEmail(), admin.getRole(), admin.getRegistration(), admin.getIdSchool());
	}
}
