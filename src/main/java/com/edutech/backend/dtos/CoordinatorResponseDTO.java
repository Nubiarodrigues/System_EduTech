package com.edutech.backend.dtos;

import com.edutech.backend.entities.Coordinator;
import com.edutech.backend.enuns.TeachingState;

public record CoordinatorResponseDTO(Long id, String name, TeachingState modality) {

	public CoordinatorResponseDTO(Coordinator coordinator) {
		this(coordinator.getId(), coordinator.getName(), coordinator.getModality());
	}
}
