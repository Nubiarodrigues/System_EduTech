package com.edutech.backend.dtos;

import com.edutech.backend.entities.Coordinator;
import com.edutech.backend.enuns.RoleUser;
import com.edutech.backend.enuns.TeachingState;

public record CoordinatorResponseDTO(Long id, String name, String email, RoleUser role, String address, TeachingState modality, String registration) {

	public CoordinatorResponseDTO(Coordinator coordinator) {
		this(
				coordinator.getId(),
				coordinator.getName(),
				coordinator.getEmail(),
				coordinator.getRole(),
				coordinator.getAddress(),
				coordinator.getModality(),
				coordinator.getRegistration());
	}
}
