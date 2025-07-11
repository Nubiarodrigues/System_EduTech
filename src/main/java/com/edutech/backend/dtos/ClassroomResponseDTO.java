package com.edutech.backend.dtos;

import com.edutech.backend.entities.Classroom;
import com.edutech.backend.entities.Discipline;
import com.edutech.backend.entities.Student;

import java.util.List;

public record ClassroomResponseDTO(
		Long id,
		Integer series,
		String identifierSeries,
		List<Discipline> discipline,
		List<Student> students,
		String coordinatorName) {

	public ClassroomResponseDTO(Classroom classroom) {
		this(
				classroom.getId(),
				classroom.getSeries(),
				classroom.getIdentifierSeries(),
				classroom.getDisciplines(),
				classroom.getStudents(),
				classroom.getCoordinatorClass() != null ? classroom.getCoordinatorClass().getName() : null);
	}
}
