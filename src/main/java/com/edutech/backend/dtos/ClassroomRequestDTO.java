package com.edutech.backend.dtos;

import com.edutech.backend.enuns.Shift;
import com.edutech.backend.enuns.TeachingState;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClassroomRequestDTO(
		@NotNull Integer series,
		@NotBlank String identifierSeries,
		@NotNull Integer capacity,
		@NotNull(message = "Shift é obrigátorio") Shift shift,
		@NotNull Integer schoolYear,
		@NotNull(message = "TeachingState é obrigátorio") TeachingState modality)
{}
