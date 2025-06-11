package com.edutech.backend.dtos;

import com.edutech.backend.entities.Classroom;
import com.edutech.backend.enuns.Shift;
import com.edutech.backend.enuns.TeachingState;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClassroomRequestDTO(
		@NotNull Integer series,
		@NotBlank String identifierSeries,
		@NotNull Integer capacity,
		@NotNull (message = "Shift é obrigátorio") Shift shift,
		@NotNull Integer schoolYear,
		@NotNull (message = "TeachingState é obrigátorio") TeachingState modality
	) {

	public ClassroomRequestDTO (Classroom classroom) {
		this (
				classroom.getSeries(),
				classroom.getIdentifierSeries(),
				classroom.getCapacity(),
				classroom.getShift(),
				classroom.getSchoolYear(),
				classroom.getModality()
		);
	}
}
