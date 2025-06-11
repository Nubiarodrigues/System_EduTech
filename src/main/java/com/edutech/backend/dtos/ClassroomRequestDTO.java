package com.edutech.backend.dtos;

import com.edutech.backend.entities.Classroom;
import com.edutech.backend.enuns.Shift;
import com.edutech.backend.enuns.TeachingState;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClassroomRequestDTO(
		@NotNull Integer series,
		@NotBlank String identifierSeries,
		@NotBlank Integer capacity,
		@NotBlank Shift shift,
		@NotNull Integer schoolYear,
		@NotBlank TeachingState modality
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
