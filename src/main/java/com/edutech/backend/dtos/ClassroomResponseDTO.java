package com.edutech.backend.dtos;

import com.edutech.backend.entities.Classroom;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClassroomResponseDTO(Long id, @NotNull Integer series, @NotBlank String identifierSeries) {

	public ClassroomResponseDTO(Classroom classroom) {
		this(classroom.getId(), classroom.getSeries(), classroom.getIdentifierSeries());
	}
}
