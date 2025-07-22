package com.edutech.backend.dtos.classroom;

import com.edutech.backend.dtos.schoolnotice.SchoolNoticesResponseDTO;
import com.edutech.backend.entities.Classroom;
import com.edutech.backend.entities.Discipline;
import com.edutech.backend.entities.Student;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record ClassroomResponseDTO(
		Long id,
		Integer series,
		String identifierSeries,
		List<Discipline> disciplines,
		List<Student> students,
		String coordinatorName,
		List<SchoolNoticesResponseDTO>  schoolNotices) {

	public ClassroomResponseDTO(Classroom classroom) {
		this(
				classroom.getId(),
				classroom.getSeries(),
				classroom.getIdentifierSeries(),
				classroom.getDisciplines(),
				classroom.getStudents(),
				classroom.getCoordinatorClass() != null ? classroom.getCoordinatorClass().getName() : null,
				classroom.getSchoolNotices() != null ? classroom.getSchoolNotices().stream().map(SchoolNoticesResponseDTO::new).collect(Collectors.toList()) : Collections.emptyList());
	}
}
