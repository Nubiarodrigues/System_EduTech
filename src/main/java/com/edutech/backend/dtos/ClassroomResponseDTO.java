package com.edutech.backend.dtos;

import java.util.List;

import com.edutech.backend.entities.Classroom;
import com.edutech.backend.entities.Student;

public record ClassroomResponseDTO(
		Long id,
		Integer series,
		String identifierSeries,
		List<Student> students,
		String coordinatorName) {

	public ClassroomResponseDTO(Classroom classroom) {
		this(
				classroom.getId(),
				classroom.getSeries(),
				classroom.getIdentifierSeries(),
				classroom.getStudents(),
				classroom.getCoordinatorClass() != null ? classroom.getCoordinatorClass().getName() : null);
	}
}
