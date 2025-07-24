package com.edutech.backend.dtos.coordinator;

import com.edutech.backend.entities.Coordinator;
import com.edutech.backend.enuns.RoleUser;
import com.edutech.backend.enuns.TeachingStage;

public record CoordinatorResponseDTO(Long id, String name, String email, RoleUser role, String address, TeachingStage modality, String registration) {

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
