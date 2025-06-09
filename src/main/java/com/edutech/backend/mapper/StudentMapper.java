package com.edutech.backend.mapper;

import com.edutech.backend.dtos.StudentRequestDTO;
import com.edutech.backend.entities.Student;

public class StudentMapper {

	 public static Student toEntity(StudentRequestDTO dto) {
	        return new Student(
	            dto.name(),
	            dto.dateBirth(),
	            dto.cpf(),
	            dto.sus(),
	            dto.address(),
	            dto.telephone(),
	            dto.emailResponsable(),
	            dto.motherName(),
	            dto.fatherName()
	        );
	    }
}
