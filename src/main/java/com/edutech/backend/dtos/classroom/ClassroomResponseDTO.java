package com.edutech.backend.dtos.classroom;

import com.edutech.backend.dtos.discipline.DisciplineSimpleResponseDTO;
import com.edutech.backend.dtos.schoolnotice.SchoolNoticesResponseDTO;
import com.edutech.backend.dtos.student.StudentSimpleResponseDTO;
import com.edutech.backend.entities.Classroom;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record ClassroomResponseDTO(
		Long id,
		Integer series,
		String identifierSeries,
		List<DisciplineSimpleResponseDTO> disciplines,
		List<StudentSimpleResponseDTO> students,
		String coordinatorName,
		List<SchoolNoticesResponseDTO>  schoolNotices) {

	public ClassroomResponseDTO(Classroom classroom) {
		this(
				classroom.getId(),
				classroom.getSeries(),
				classroom.getIdentifierSeries(),
				classroom.getDisciplines() != null ? classroom.getDisciplines().stream().map(DisciplineSimpleResponseDTO::new).collect(Collectors.toList()) : Collections.emptyList(),
				classroom.getStudents() != null ? classroom.getStudents().stream().map(StudentSimpleResponseDTO::new).collect(Collectors.toList()) : Collections.emptyList(),
				classroom.getCoordinatorClass() != null ? classroom.getCoordinatorClass().getName() : null,
				classroom.getSchoolNotices() != null ? classroom.getSchoolNotices().stream().map(SchoolNoticesResponseDTO::new).collect(Collectors.toList()) : Collections.emptyList());
	}
}
