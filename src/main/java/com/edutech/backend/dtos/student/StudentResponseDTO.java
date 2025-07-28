package com.edutech.backend.dtos.student;

import com.edutech.backend.dtos.bimestergrade.BimesterGradeResponseDTO;
import com.edutech.backend.dtos.disciplinaryrecord.DisciplinaryRecordResponseDTO;
import com.edutech.backend.dtos.frequency.FrequencyResponseDTO;
import com.edutech.backend.entities.Student;
import com.edutech.backend.enuns.RoleUser;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record StudentResponseDTO(
		Long id,
		String name,
		String email,
		RoleUser role,
		String registration,
		List<BimesterGradeResponseDTO> bimesters,
		List<DisciplinaryRecordResponseDTO> history,
		List<FrequencyResponseDTO> frequencies)
{

	public StudentResponseDTO(Student student) {
		this(
				student.getId(),
				student.getName(),
				student.getEmail(),
				student.getRole(),
				student.getRegistration(),
				student.getBimesters() != null ? student.getBimesters().stream().map(BimesterGradeResponseDTO::new).collect(Collectors.toList()) : Collections.emptyList(),
				student.getDisciplinaryHistory() != null ? student.getDisciplinaryHistory().stream().map(DisciplinaryRecordResponseDTO::new).collect(Collectors.toList()) : Collections.emptyList(),
				student.getFrequencyHistory() != null ? student.getFrequencyHistory().stream().map(FrequencyResponseDTO::new).collect(Collectors.toList()) : Collections.emptyList()
			);
	}
}
