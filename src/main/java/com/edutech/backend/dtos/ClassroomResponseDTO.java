package com.edutech.backend.dtos;

import java.util.List;

import com.edutech.backend.entities.Classroom;
import com.edutech.backend.entities.Student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClassroomResponseDTO(Long id, @NotNull Integer series, @NotBlank String identifierSeries, List<Student> students) {

	public ClassroomResponseDTO(Classroom classroom) {
		this(
				classroom.getId(),
				classroom.getSeries(),
				classroom.getIdentifierSeries(),
				classroom.getStudents());
	}
}
